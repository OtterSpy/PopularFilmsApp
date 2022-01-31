package com.example.popularfilmsapp.di

import com.example.popularfilmsapp.presentation.ui.activities.MainActivity
import com.example.popularfilmsapp.utils.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, ViewModelsModule::class])
interface AppComponent {

    val factory: ViewModelFactory

    fun inject(activity: MainActivity)
}