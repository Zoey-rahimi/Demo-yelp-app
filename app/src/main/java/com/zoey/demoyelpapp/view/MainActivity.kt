package com.zoey.demoyelpapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zoey.demoyelpapp.R
import com.zoey.demoyelpapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private val listAdapter = ListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.refresh()

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        refreshLayout.setOnRefreshListener {
            recycler_view.visibility = View.GONE
            error_view.visibility = View.GONE
            loading_bar.visibility = View.VISIBLE
            mainViewModel.refresh()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.businessList.observe(this, Observer { businessList ->
            businessList?.let {
                recycler_view.visibility = View.VISIBLE
                listAdapter.updateBusinessList(businessList)
            }
        })

        mainViewModel.loadingError.observe(this, Observer { isError ->
            isError?.let {
                error_view.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        mainViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_bar.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    recycler_view.visibility = View.GONE
                    error_view.visibility = View.GONE
                }
            }
        })
    }
}
