package com.example.popularfilmsapp.domain.usecases

import com.example.popularfilmsapp.domain.model.ActorDetails
import com.example.popularfilmsapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetActorDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(personId: Int, apiKey: String): ActorDetails {
        return repository.getActorDetails(personId, apiKey)
    }
}