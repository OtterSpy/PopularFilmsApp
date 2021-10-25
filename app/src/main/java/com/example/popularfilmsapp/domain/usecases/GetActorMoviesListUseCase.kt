package com.example.popularfilmsapp.domain.usecases

import com.example.popularfilmsapp.domain.model.ActorMovieCast
import com.example.popularfilmsapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetActorMoviesListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(personId: Int, apiKey: String): List<ActorMovieCast> {
        return try {
            repository.getActorMoviesList(personId, apiKey).cast
        } catch (t: Throwable) {
            emptyList()
        }
    }
}