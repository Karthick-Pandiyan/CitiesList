package com.kp.worldcities.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.textChanges
import com.kp.worldcities.R
import com.kp.worldcities.model.Cities
import com.kp.worldcities.ui.adapter.CitiesRecyclerAdapter
import com.kp.worldcities.viewmodel.CitiesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CitiesViewModel
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(CitiesViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getAllCities.observe(this, Observer { citiesList ->
            citiesList?.let {
                progressBar.visibility = View.GONE
                viewModel.configureCitiesData(it)
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
                viewModel.search(it.toString())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        viewModel.existingCitiesList.clear()
                        viewModel.existingCitiesList.addAll(viewModel.filteredCitiesList)
                        setCitiesListAdapter(viewModel.existingCitiesList)
                    }.addTo(disposable)
            }.addTo(disposable)
    }

    private fun setCitiesListAdapter(it: List<Cities>) {
        recyclerView.adapter = CitiesRecyclerAdapter(this, it)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
