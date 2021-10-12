package com.example.popularfilmsapp.presentation.ui

import android.app.Activity
import android.app.Application
import com.example.popularfilmsapp.di.AppComponent
import com.example.popularfilmsapp.di.DaggerAppComponent

class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }

    companion object {
        fun Activity.getAppComponent() = (application as MainApplication).appComponent
    }

}