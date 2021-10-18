package com.example.popularfilmsapp.di

import androidx.lifecycle.ViewModel
import com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment.ActorDetailsViewModel
import com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment.ActorMoviesListCastViewModel
import com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment.ActorsListViewModel
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

    @Binds
    @[IntoMap ClassKey(ActorsListViewModel::class)]
    fun provideActorsListViewModel(actorsListViewModel: ActorsListViewModel): ViewModel

    @Binds
    @[IntoMap ClassKey(ActorDetailsViewModel::class)]
    fun provideActorDetailsViewModel(actorsDetailsViewModel: ActorDetailsViewModel): ViewModel

    @Binds
    @[IntoMap ClassKey(ActorMoviesListCastViewModel::class)]
    fun provideActorMoviesViewModel(actorMoviesListCastViewModel: ActorMoviesListCastViewModel): ViewModel
}