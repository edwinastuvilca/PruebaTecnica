package com.eastuvilca.pruebatecnica.apiclient

import android.content.Context
import com.android.volley.Response
import com.eastuvilca.pruebatecnica.model.UserApp

class RequestApi (context: Context) : BaseApi(context)  {
    fun validateEmail(onOkEvent: Response.Listener<String?>, onError: Response.ErrorListener){
        val params: MutableMap<String, String> = HashMap()
        params["page"] = "1"
        params["per_page"] = "1500"
        ejecutarGet("users", params, onOkEvent, onError)
    }

    fun createUser(obj: UserApp, onOkEvent: Response.Listener<String?>, onError: Response.ErrorListener){
        val params: MutableMap<String, String> = HashMap()
        params["name"] = obj.getName()
        params["job"] = obj.email /* Solo para efectos de muestra */
        ejecutarPost("users", params, onOkEvent, onError)
    }

    fun login(obj: UserApp, onOkEvent: Response.Listener<String?>, onError: Response.ErrorListener){
        val params: MutableMap<String, String> = HashMap()
        params["email"] = obj.email
        params["password"] = obj.password
        ejecutarPost("login", params, onOkEvent, onError)
    }
}