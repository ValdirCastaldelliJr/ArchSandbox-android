package com.castaldelli.archsandbox.core


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.castaldelli.archsandbox.repository.Service


open class CoreViewModel : ViewModel(){
    protected val service by lazy {  Service.create() }

    protected val _onResponse = MutableLiveData<CoreModel>()
    val onResponse : LiveData<CoreModel>
        get() = _onResponse

    protected val _onFailureMessage = MutableLiveData<String>()
    val onFailureMessage: LiveData<String>
        get() = _onFailureMessage

}