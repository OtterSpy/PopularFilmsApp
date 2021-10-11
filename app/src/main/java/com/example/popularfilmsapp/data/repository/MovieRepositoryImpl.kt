package com.example.popularfilmsapp.data.repository

import com.example.popularfilmsapp.data.remote.TheMovieDbApi
import com.example.popularfilmsapp.data.remote.dto.toMovieList
import com.example.popularfilmsapp.domain.model.MovieList
import com.example.popularfilmsapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val theMovieDbApi: TheMovieDbApi
) : MovieRepository {

    override suspend fun getMoviesList(apiKey: String, page: Int): MovieList =
        theMovieDbApi.getMovieList(apiKey, page).toMovieList()
}