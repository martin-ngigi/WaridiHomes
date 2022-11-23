package com.example.waridihomes.data.util

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreference @Inject constructor(
    private val sharedPreference: SharedPreferences
) {

    fun isFirstAppLaunch(): Boolean{
        return sharedPreference.getBoolean(Constants.IS_FIRST_APP_LAUNCH, true)
    }

    fun  saveFirstAppLaunch(value: Boolean){
        sharedPreference.edit().putBoolean(Constants.IS_FIRST_APP_LAUNCH, value).apply()
    }

    fun userIsLoggedIn(): Boolean{
        val token = sharedPreference.getString(Constants.USER_IS_LOGGED_IN, null)
        return token != null
    }

    fun getUserToken(): String {
        return sharedPreference.getString(Constants.USER_IS_LOGGED_IN, "").toString()
    }

    fun saveUserAccessToken(token: String){
        sharedPreference.edit().putString(Constants.USER_IS_LOGGED_IN, token).apply()
    }

    fun deleteAccessToken(): Boolean{
        sharedPreference.edit().remove(Constants.USER_IS_LOGGED_IN).apply()
        return userIsLoggedIn()
    }

}