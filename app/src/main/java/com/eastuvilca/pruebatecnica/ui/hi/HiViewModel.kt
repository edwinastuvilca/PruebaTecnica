package com.eastuvilca.pruebatecnica.ui.hi

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.eastuvilca.pruebatecnica.apiclient.RequestApi
import com.eastuvilca.pruebatecnica.hooks.Converter
import com.eastuvilca.pruebatecnica.navigation.AppView

class HiViewModel(private var navController: NavController) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _continueEnable = MutableLiveData<Boolean>()
    val continueEnable: LiveData<Boolean> = _continueEnable

    fun onEmailChanged(email: String) {
        _email.value = email
        _continueEnable.value = isValidEmail(email)
    }

    private fun isValidEmail(email: String): Boolean  = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun onContinueClick() {
        _continueEnable.value = false
        val requestApi = RequestApi(navController.context)
        requestApi.validateEmail({response ->
            val items = Converter.stringToUserResponseList(response!!)
            val encontrado = items.findLast { item -> item.email == email.value }
            if (encontrado == null) {
                navController.navigate(route = AppView.signInScreen.route + "/${email.value}")
            }else {
                val data = Converter.stringToBase64(encontrado.toString())
                navController.navigate(route = AppView.loginScreen.route + "/${data}")
            }
            _continueEnable.value = true
        }){
            _continueEnable.value = true
        }
    }
}