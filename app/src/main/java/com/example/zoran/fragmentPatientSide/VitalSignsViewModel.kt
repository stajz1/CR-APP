package com.example.zoran.fragmentPatientSide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.diagnosis.DiagnosisModel
import com.example.zoran.fragmentStaffSide.StoreDataResponseModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class VitalSignsViewModel: ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun storeDiagnosis(patientId: Int, vitalSigns: String, value: String, maxValue: String) {
        ApiRepository.storeVitalSigns(patientId, vitalSigns, value, maxValue)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<StoreDataResponseModel>() {

                override fun onSuccess(model: StoreDataResponseModel) {
                    response.postValue(NetworkResponse.SUCCESS.AddVitalSigns(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }
    fun vitalSignsList(patientId: Int){
        ApiRepository.vitalSignsList(patientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<VitalSignsListModel>>() {

                override fun onSuccess(model: ArrayList<VitalSignsListModel>) {
                    response.postValue(NetworkResponse.SUCCESS.VitalSignsList(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }
}