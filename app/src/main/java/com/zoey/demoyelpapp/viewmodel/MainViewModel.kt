package com.zoey.demoyelpapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zoey.demoyelpapp.model.Business
import com.zoey.demoyelpapp.model.Businesses
import com.zoey.demoyelpapp.model.YelpApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val yelpApiService = YelpApiService()
    private val disposable = CompositeDisposable()

    val businessList = MutableLiveData<List<Business>>()
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true

        disposable.add(
            yelpApiService.getBusinesses()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Businesses>() {
                    override fun onSuccess(businesses: Businesses) {
                        Log.d("fetchFromRemoteSuccess:", businesses.total.toString())
                        loading.value = false
                        loadingError.value = false
                        businessList.value = businesses.businesses
                    }

                    override fun onError(e: Throwable) {
                        Log.e("fetchFromRemoteError:", e.message)
                        loadingError.value = true
                        loading.value = false
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}