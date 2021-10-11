package com.example.popularfilmsapp.di

import com.example.popularfilmsapp.data.repository.MovieRepositoryImpl
import com.example.popularfilmsapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}