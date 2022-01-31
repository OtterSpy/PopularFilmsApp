package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.domain.usecases.GetMoviesListUseCase
import com.example.popularfilmsapp.presentation.helpers.paging.MoviePagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMoviesListUseCase: GetMoviesListUseCase) :
    ViewModel() {

    companion object {
        const val INPUT_TIME_HOLD = 2000L
    }

    private val _movies = MutableLiveData<PagingData<MovieItem>>()

    val movies: LiveData<PagingData<MovieItem>> get() = _movies
    private val queryFlow = MutableStateFlow("")

    fun passQuery(query: String) {
        viewModelScope.launch(Dispatchers.Default) { queryFlow.emit(query) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            queryFlow
                .debounce(INPUT_TIME_HOLD)
                .collect {
                    setQuery(it)
                }
        }
    }

    private fun setQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Pager(PagingConfig(pageSize = 20)) {
                MoviePagingSource(getMoviesListUseCase).apply {
                    this.query = query
                }
            }.flow
                .cachedIn(viewModelScope)
                .collect {
                    _movies.postValue(it)
                }
        }
    }
}