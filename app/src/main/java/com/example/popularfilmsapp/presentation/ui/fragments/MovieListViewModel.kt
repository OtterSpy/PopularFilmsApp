package com.example.popularfilmsapp.presentation.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.domain.usecases.GetMoviesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMoviesListUseCase: GetMoviesListUseCase) :
    ViewModel() {

    private val _movies = MutableLiveData<Resource<List<MovieItem>>>()
    val movies: LiveData<Resource<List<MovieItem>>>
        get() = _movies

    fun getMovies(apiKey: String, page: Int) {
        _movies.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.postValue(Resource.success(getMoviesListUseCase(apiKey, page)))
            } catch (t: Throwable) {
                _movies.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }


    }

}