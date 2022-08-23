package com.example.qnachlocal.data.local

import android.content.Context
import android.content.SharedPreferences
import com.chola.app.data.dto.login.LoginResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        const val PREFS_NAME = "ugro"
        const val LOGIN_ = "login_"
        const val FCM_TOKEN = "fcm_token"
        const val AUTH_TOKEN = "auth_token"
    }


    fun logOut() {
        val editor: SharedPreferences.Editor
        val settings: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        editor = settings.edit()
        editor.clear()
        editor.apply()
    }


    fun storeUserDetail(user: LoginResponse?) {
        val editor: SharedPreferences.Editor
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        if (user != null) {
            authToken(user.data.user_id)
        }
        editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(LOGIN_, json)
        editor.apply()
    }

    fun getUserDetail(): LoginResponse? {
        try {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val gson = Gson()
            val json = sharedPref.getString(LOGIN_, "")

            return gson.fromJson(json, LoginResponse::class.java)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }

        return null

    }

    fun isFirstTime(isFirstTime: Boolean) {
        val editor: SharedPreferences.Editor
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        editor = sharedPref.edit()
        editor.putBoolean("isFirst", isFirstTime)
        editor.apply()
    }

    fun getFirstTime(): Boolean {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        return sharedPref.getBoolean("isFirst", false)


    }

    // Store Firebase Token
    fun storeFcmToken(token: String) {
        val editor: SharedPreferences.Editor
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        editor = sharedPref.edit()
        editor.putString(FCM_TOKEN, token)
        editor.apply()
    }

    // get Firebase Token
    fun getFcmToken(): String? {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        return sharedPref.getString(FCM_TOKEN, "")


    }

    // Store Authorization Token
    fun authToken(token: String) {
        val editor: SharedPreferences.Editor
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        editor = sharedPref.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }

    // get Authorization Token
    fun getAuthToken(): String? {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        return sharedPref.getString(AUTH_TOKEN, "")


    }


}