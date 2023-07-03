package com.eastuvilca.pruebatecnica.ui.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.eastuvilca.pruebatecnica.apiclient.RequestApi
import com.eastuvilca.pruebatecnica.component.BaseViewModel
import com.eastuvilca.pruebatecnica.hooks.Converter
import com.eastuvilca.pruebatecnica.model.UserApp
import com.eastuvilca.pruebatecnica.navigation.AppView

class LoginViewModel(private var navController: NavController, data: String?) : BaseViewModel() {

    private val user = Converter.stringToUserResponse(Converter.base64ToString(data!!))

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isContinueEnable = MutableLiveData<Boolean>()
    val isContinueEnable: LiveData<Boolean> = _isContinueEnable

    fun getNombre(): String { return user.firstName + " " + user.lastName}
    fun getEmail(): String { return user.email }
    fun getAvatar(): String { return user.avatar }

    fun onPasswordChanged(password: String) {
        _password.value = password
        _isContinueEnable.value = isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    fun onContinueClick() {
        _isContinueEnable.value = false
        val requestApi = RequestApi(navController.context)
        val userApp = UserApp()
        userApp.email = user.email
        userApp.password = password.value!!
        requestApi.login(userApp, { response ->
            Toast.makeText(navController.context, response, Toast.LENGTH_LONG).show()
            navController.navigate(route = AppView.mainScreen.route + "/9999") {
                popUpTo(navController.graph.id){
                    saveState = true
                }
            }
        }){
            _isContinueEnable.value = true
        }
    }
}