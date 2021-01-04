package com.example.zoran.diagnosis

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.fragmentStaffSide.StoreDataResponseModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DiagnosisViewModel: ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun storeDiagnosis(patientId: Int, newDiagnosis: String, date: String, therapy: String) {
        ApiRepository.storeDiagnosis(patientId, newDiagnosis, date, therapy)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<StoreDataResponseModel>() {

                override fun onSuccess(model: StoreDataResponseModel) {
                    response.postValue(NetworkResponse.SUCCESS.AddDiagnosis(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

    fun diagnosisList(patientId: Int){
        ApiRepository.diagnosisList(patientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<DiagnosisModel>>() {

                override fun onSuccess(model: ArrayList<DiagnosisModel>) {
                    response.postValue(NetworkResponse.SUCCESS.DiagnosisList(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

    fun deleteDiagnosis(id: Int){
        ApiRepository.deleteDiagnosis(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<StoreDataResponseModel>() {

                override fun onSuccess(model: StoreDataResponseModel) {
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}