package com.example.zoran.fragmentStaffSide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PatientListViewModel : ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun patientList() {
        ApiRepository.patientList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<PatientListModel>>() {

                override fun onSuccess(model: ArrayList<PatientListModel>) {
                    response.postValue(NetworkResponse.SUCCESS.PatientList(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}