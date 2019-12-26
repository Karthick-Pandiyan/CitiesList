package com.kp.androidworldcities.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kp.androidworldcities.R
import com.kp.androidworldcities.ui.adapter.CitiesRecyclerAdapter
import com.kp.androidworldcities.viewmodel.CitiesViewModel

import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var citiesViewModel: CitiesViewModel
    lateinit var citiesAdapter : CitiesRecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        citiesViewModel = ViewModelProviders.of(this).get(CitiesViewModel::class.java)
        setupRecyclerAdapter()

        citiesViewModel.getCitiesList.observe(this, Observer { citiesList ->

        })


    }

    private fun setupRecyclerAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        citiesAdapter = CitiesRecyclerAdapter(this, listOf())
        recyclerView.adapter = citiesAdapter
    }
}
