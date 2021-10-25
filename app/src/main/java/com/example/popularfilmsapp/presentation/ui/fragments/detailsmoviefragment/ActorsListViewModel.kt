package com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.domain.model.Cast
import com.example.popularfilmsapp.domain.usecases.GetActorsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActorsListViewModel @Inject constructor(
    val getActorsListUseCase: GetActorsListUseCase
) : ViewModel() {

    private val _actors = MutableLiveData<Resource<List<Cast>>>()
    val actors: LiveData<Resource<List<Cast>>>
        get() = _actors

    fun getActors(movieId: Int, apiKey: String) {
        _actors.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _actors.postValue(Resource.success(getActorsListUseCase(movieId, apiKey)))
                Log.d("myLogs", "getActors: ${getActorsListUseCase(movieId, apiKey)}")
            } catch (t: Throwable) {
                _actors.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }
}