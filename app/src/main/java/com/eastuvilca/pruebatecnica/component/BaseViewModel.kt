package com.eastuvilca.pruebatecnica.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    private val _isPasswordVisible = MutableLiveData<Boolean>()
    val isPasswordVisible: LiveData<Boolean> = _isPasswordVisible

    fun onClickViewPassword(){
        var visibility = false
        if ( isPasswordVisible.value != null){
            visibility = isPasswordVisible.value!!
        }
        _isPasswordVisible.value = !visibility
    }
}