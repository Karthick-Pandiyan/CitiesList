package com.kp.androidworldcities.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kp.androidworldcities.R
import com.kp.androidworldcities.viewmodel.CitiesViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var citiesViewModel: CitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        citiesViewModel = ViewModelProviders.of(this).get(CitiesViewModel::class.java)

        citiesViewModel.getCitiesList.observe(this, Observer {

        })


    }
}
