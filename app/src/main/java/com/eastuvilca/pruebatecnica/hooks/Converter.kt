package com.eastuvilca.pruebatecnica.hooks

import android.util.Base64
import com.eastuvilca.pruebatecnica.model.CreateUserResponse
import com.eastuvilca.pruebatecnica.model.UserResponse
import org.json.JSONObject

class Converter {
    companion object{

        fun stringToBase64(value: String): String {
            return Base64.encodeToString(value.toByteArray(), Base64.DEFAULT)
        }

        fun base64ToString(value: String): String {
            val data = Base64.decode(value.toByteArray(), Base64.DEFAULT)
            return String(data)
        }

        fun stringToUserResponse(response: String): UserResponse{
            val item = JSONObject(response)
            val userResponse = UserResponse()
            userResponse.userID = item.getInt("id")
            userResponse.email = item.getString("email")
            userResponse.firstName = item.getString("first_name")
            userResponse.lastName = item.getString("last_name")
            userResponse.avatar = item.getString("avatar")
            return userResponse
        }

        fun stringToUserResponseList(response: String): List<UserResponse>{
            val itemsResponse: ArrayList<UserResponse> = ArrayList()
            val jsonObject = JSONObject(response)
            val jsonItems = jsonObject.getJSONArray("data")
            for (pos in 0 until jsonItems.length()) {
                val item = jsonItems.getJSONObject(pos)
                val userResponse = stringToUserResponse(item.toString())
                userResponse.base = item.toString()
                itemsResponse.add(userResponse)
            }
            return itemsResponse
        }

        fun stringToCreateUserResponse(response: String): CreateUserResponse {
            val item = JSONObject(response)
            val createUserResponse = CreateUserResponse()
            createUserResponse.id = item.getInt("id")
            createUserResponse.name = item.getString("name")
            createUserResponse.job = item.getString("job")
            createUserResponse.createdAt = item.getString("createdAt")
            return createUserResponse
        }
    }
}