package com.eastuvilca.pruebatecnica.ui.signup

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.eastuvilca.pruebatecnica.apiclient.RequestApi
import com.eastuvilca.pruebatecnica.component.BaseViewModel
import com.eastuvilca.pruebatecnica.hooks.Converter
import com.eastuvilca.pruebatecnica.model.UserApp
import com.eastuvilca.pruebatecnica.navigation.AppView

class SignUpViewModel(private var navController: NavController, private val email: String) : BaseViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _agreeEnable = MutableLiveData<Boolean>()
    val agreeEnable: LiveData<Boolean> = _agreeEnable

    fun getEmail(): String{ return this.email }

    fun onAgreeClick() {
        _agreeEnable.value = false
        val requestApi = RequestApi(navController.context)
        val userApp = UserApp()
        userApp.email = this.email
        userApp.firstName = userName.value!!
        userApp.lastName = password.value!!
        requestApi.createUser(userApp, { response ->
            val obj = Converter.stringToCreateUserResponse(response!!)
            if (obj.id == 0){
                Toast.makeText(navController.context, "No se ha podido registrar el usuario",
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(navController.context, obj.createdAt, Toast.LENGTH_LONG).show()
                navController.navigate(route = AppView.mainScreen.route + "/${obj.id}")
                _agreeEnable.value = true
            }
        }){
            _agreeEnable.value = true
        }
    }

    fun onUserNameChanged(username: String?) {
        _userName.value = username
        _agreeEnable.value = validUsarioPassword(username, _password.value)
    }

    fun onPasswordChanged(password: String?) {
        _password.value = password
        _agreeEnable.value = validUsarioPassword(_userName.value , password!!)
    }

    private fun validUsarioPassword(pUsername: String?, pPassword: String?): Boolean{
        if(pUsername.isNullOrBlank()) return false
        if(pPassword.isNullOrBlank()) return false
        return (pUsername.length > 5) && (pPassword.length > 4)
    }
}