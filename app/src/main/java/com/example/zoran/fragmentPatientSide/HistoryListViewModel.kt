package com.example.zoran.fragmentPatientSide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.fragmentStaffSide.StoreDataResponseModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HistoryListViewModel : ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun historyList(patientId: Int) {

        ApiRepository.workOutList(patientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<HistoryListModel>>() {

                override fun onSuccess(model: ArrayList<HistoryListModel>) {
                    response.postValue(NetworkResponse.SUCCESS.HistoryList(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}