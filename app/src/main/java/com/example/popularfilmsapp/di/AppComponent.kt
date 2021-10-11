package com.example.popularfilmsapp.di

import com.example.popularfilmsapp.presentation.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {
     fun inject(activity: MainActivity)
}