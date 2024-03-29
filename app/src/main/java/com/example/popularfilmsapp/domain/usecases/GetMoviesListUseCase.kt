package com.example.popularfilmsapp.domain.usecases

import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(apiKey: String, query: String, page: Int): List<MovieItem> {
        return try {
            if (query != "") {
                repository.getSearchList(query, apiKey, page).results
            } else {
                repository.getMoviesList(apiKey, page).results
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }
}