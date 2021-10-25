package com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.domain.model.ActorMovieCast
import com.example.popularfilmsapp.domain.usecases.GetActorMoviesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActorMoviesListCastViewModel @Inject constructor(
    val getActorMoviesListUseCase: GetActorMoviesListUseCase
) : ViewModel() {
    private val _actorFilms = MutableLiveData<Resource<List<ActorMovieCast>>>()
    val actorFilms: LiveData<Resource<List<ActorMovieCast>>>
        get() = _actorFilms

    fun getActorFilms(personId: Int) {
        _actorFilms.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _actorFilms.postValue(
                    Resource.success(
                        getActorMoviesListUseCase(
                            personId,
                            Constants.API_KEY
                        )
                    )
                )
                Log.d(
                    "myLogs",
                    "getActorFilms: ${getActorMoviesListUseCase(personId, Constants.API_KEY)}"
                )
            } catch (t: Throwable) {
                _actorFilms.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }
}