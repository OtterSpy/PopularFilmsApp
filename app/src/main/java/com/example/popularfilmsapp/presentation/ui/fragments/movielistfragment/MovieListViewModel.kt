package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.popularfilmsapp.presentation.helpers.paging.MoviePagingSource
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val moviePagingSource: MoviePagingSource) :
    ViewModel() {

    /*private val _movies = MutableLiveData<Resource<PagingData<MovieItem>>>()
    val moviess: LiveData<Resource<PagingData<MovieItem>>>
        get() = _movies*/

    val movies = Pager(PagingConfig(pageSize = 20)) {
        moviePagingSource
    }.liveData.cachedIn(viewModelScope)

    /*fun getMovies(page: Int) {
        _movies.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.postValue(Resource.success(movies.value!!))
                Log.d("myLogs", "getMovies: ${movies.value}")
            } catch (t: Throwable) {
                _movies.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }


    }*/

}