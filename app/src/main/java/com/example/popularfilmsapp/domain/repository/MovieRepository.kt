package com.example.popularfilmsapp.domain.repository

import com.example.popularfilmsapp.data.remote.dto.ActorsListDto
import com.example.popularfilmsapp.domain.model.ActorsList
import com.example.popularfilmsapp.domain.model.MovieList

interface MovieRepository {

    suspend fun getMoviesList(apiKey: String, page: Int): MovieList

    suspend fun getActorsList(movieId: Int, apiKey: String): ActorsList

}