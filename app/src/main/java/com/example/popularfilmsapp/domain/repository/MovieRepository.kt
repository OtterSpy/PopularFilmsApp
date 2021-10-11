package com.example.popularfilmsapp.domain.repository

import com.example.popularfilmsapp.domain.model.MovieList

interface MovieRepository {

    suspend fun getMoviesList(apiKey: String, page: Int): MovieList

}