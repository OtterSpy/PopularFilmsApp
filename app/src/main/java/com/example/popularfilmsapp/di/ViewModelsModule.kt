package com.example.popularfilmsapp.di

import androidx.lifecycle.ViewModel
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {

    @Binds
    @[IntoMap ClassKey(MovieListViewModel::class)]
    fun provideMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

}