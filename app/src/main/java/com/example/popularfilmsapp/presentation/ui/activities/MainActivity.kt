package com.example.popularfilmsapp.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.domain.usecases.GetMoviesListUseCase
import com.example.popularfilmsapp.presentation.ui.MainApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getMoviesListUseCase: GetMoviesListUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).appComponent.inject(this)
    }
}