package com.example.popularfilmsapp.domain.repository

import com.example.popularfilmsapp.domain.model.ActorDetails
import com.example.popularfilmsapp.domain.model.ActorMoviesList
import com.example.popularfilmsapp.domain.model.ActorsList
import com.example.popularfilmsapp.domain.model.MovieList

interface MovieRepository {

    suspend fun getMoviesList(apiKey: String, page: Int): MovieList

    suspend fun getActorsList(movieId: Int, apiKey: String): ActorsList

    suspend fun getActorMoviesList(personId: Int, apiKey: String): ActorMoviesList

    suspend fun getActorDetails(personId: Int, apiKey: String): ActorDetails

    suspend fun getSearchList(query: String, apiKey: String, page: Int): MovieList

}