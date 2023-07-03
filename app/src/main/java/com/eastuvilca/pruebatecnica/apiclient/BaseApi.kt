package com.eastuvilca.pruebatecnica.apiclient

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.eastuvilca.pruebatecnica.config.ConfigApp

open class BaseApi (private val context: Context) {
    @JvmOverloads
    fun ejecutarGet(
        route: String,
        params: Map<String, String>?,
        onOKEvent: Response.Listener<String?>?,
        onErrorEvent: Response.ErrorListener? = Response.ErrorListener { error ->
            Log.w("RESP_API", "...error en el método ${error.message}")
        }
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val urlRest = ConfigApp.urlApiServer + route
        val stringRequest: StringRequest =
            object : StringRequest(Method.GET, urlRest, onOKEvent, onErrorEvent) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    return super.getHeaders()
                }

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String>? {
                    return params
                }
            }
        stringRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 6, 1,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue.add(stringRequest)
    }

    @JvmOverloads
    fun ejecutarPost(
        route: String,
        params: Map<String, String>?,
        onOKEvent: Response.Listener<String?>?,
        onErrorEvent: Response.ErrorListener? = Response.ErrorListener { error ->
            Log.w("RESP_API", "...error en el método ${error.message}")
        }
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val urlRest = ConfigApp.urlApiServer + route
        val stringRequest: StringRequest =
            object : StringRequest(Method.POST, urlRest, onOKEvent, onErrorEvent) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    return super.getHeaders()
                }

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String>? {
                    return params
                }
            }
        stringRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 6, 1,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue.add(stringRequest)
    }

    open fun getImage(url: String, evenOK: Response.Listener<Bitmap?>,
        eventError: Response.ErrorListener
    ) {
        try {
            val requestQueue = Volley.newRequestQueue(context)
            val maxWidth = 0
            val maxHeigth = 0
            val data = ImageRequest(url, evenOK, maxWidth, maxHeigth, null,
                eventError
            )
            requestQueue.add(data)
        } catch (ex: Exception) {
            Log.d("ERROR_APP", ex.message!!)
        }
    }
}