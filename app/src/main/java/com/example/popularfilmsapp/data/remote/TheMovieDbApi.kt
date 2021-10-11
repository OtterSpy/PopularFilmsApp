package com.example.popularfilmsapp.data.remote

import com.example.popularfilmsapp.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") apiKey: String, @Query("page") page: Int) : MovieListDto

}