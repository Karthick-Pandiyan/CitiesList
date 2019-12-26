package com.kp.androidworldcities.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.textChanges
import com.kp.androidworldcities.R
import com.kp.androidworldcities.model.Cities
import com.kp.androidworldcities.ui.adapter.CitiesRecyclerAdapter
import com.kp.androidworldcities.viewmodel.CitiesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.content_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var citiesViewModel: CitiesViewModel
    lateinit var citiesAdapter : CitiesRecyclerAdapter
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        citiesViewModel = ViewModelProviders.of(this).get(CitiesViewModel::class.java)
        setupRecyclerAdapter()

        citiesViewModel.getCitiesList.observe(this, Observer { citiesList ->
            citiesList?.let {
                progressBar.visibility = View.GONE
                citiesViewModel.configureCitiesData(it)
                setCitiesListAdapter(it)
                initializeSearch()
            }
        })
    }

    private fun initializeSearch() {
        edtSearchInput
            .textChanges()
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribe {
                citiesViewModel.doSearch(it.toString())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        citiesViewModel.existingCitiesList.clear()
                        citiesViewModel.existingCitiesList.addAll(citiesViewModel.filteredCitiesList)
                        setCitiesListAdapter(citiesViewModel.existingCitiesList)
                    }.addTo(disposable)
            }.addTo(disposable)
    }

    private fun setupRecyclerAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        citiesAdapter = CitiesRecyclerAdapter(this, listOf())
        recyclerView.adapter = citiesAdapter
    }

    private fun setCitiesListAdapter(citiesList: List<Cities>) {
        citiesAdapter.loadCities(citiesList)
    }
}
