package com.example.popularfilmsapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.domain.usecases.GetMoviesListUseCase
import com.example.popularfilmsapp.presentation.ui.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getMoviesListUseCase: GetMoviesListUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).appComponent.inject(this)



        testList()

    }

    private fun testList() {
        lifecycleScope.launch {
            val list = getMoviesListUseCase.invoke(Constants.API_KEY, 1)
            val listNames = ArrayList<String>()
            for (a in list) {
                listNames.add(a.title)
            }
            Log.d("myLogs", "onCreate: $listNames")
        }
    }

}