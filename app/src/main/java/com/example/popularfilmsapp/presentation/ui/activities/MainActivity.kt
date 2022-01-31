package com.example.popularfilmsapp.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.presentation.ui.MainApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).appComponent.inject(this)
    }
}