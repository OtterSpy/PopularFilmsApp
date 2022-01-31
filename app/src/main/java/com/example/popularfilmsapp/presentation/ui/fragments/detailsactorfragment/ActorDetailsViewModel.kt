package com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.domain.model.ActorDetails
import com.example.popularfilmsapp.domain.usecases.GetActorDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActorDetailsViewModel @Inject constructor(
    val getActorDetailsUseCase: GetActorDetailsUseCase
) : ViewModel() {
    private val _actorDetails = MutableLiveData<Resource<ActorDetails>>()
    val actorDetails: LiveData<Resource<ActorDetails>>
        get() = _actorDetails

    fun getActorDetails(personId: Int) {
        _actorDetails.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _actorDetails.postValue(
                    Resource.success(
                        getActorDetailsUseCase(
                            personId,
                            Constants.API_KEY
                        )
                    )
                )
            } catch (t: Throwable) {
                _actorDetails.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }
}