package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.presentation.helpers.paging.MoviePagingSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val moviePagingSource: MoviePagingSource) :
    ViewModel() {

    lateinit var movies: StateFlow<PagingData<MovieItem>>

    fun setQuery(query: String) {
        Log.d("query", "setQuery: $query")
        moviePagingSource.query = query
        movies = Pager(PagingConfig(pageSize = 20)) {
            moviePagingSource
        }.flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }
}