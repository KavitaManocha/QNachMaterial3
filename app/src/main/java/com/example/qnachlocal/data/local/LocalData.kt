package com.example.qnachlocal.data.local

import android.content.Context
import android.content.SharedPreferences
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.FAVOURITES_KEY
import com.example.qnachlocal.SHARED_PREFERENCES_FILE_NAME
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.error.PASS_WORD_ERROR
import javax.inject.Inject

/**
 * Created by Sunil
 */

class LocalData @Inject constructor(val context: Context) {



    fun getCachedFavourites(): com.chola.app.data.Resource<Set<String>> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        return com.chola.app.data.Resource.Success(sharedPref.getStringSet(FAVOURITES_KEY, setOf()) ?: setOf())
    }

    fun isFavourite(id: String): com.chola.app.data.Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val cache = sharedPref.getStringSet(FAVOURITES_KEY, setOf<String>()) ?: setOf()
        return com.chola.app.data.Resource.Success(cache.contains(id))
    }

    fun cacheFavourites(ids: Set<String>): com.chola.app.data.Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet(FAVOURITES_KEY, ids)
        editor.apply()
        val isSuccess = editor.commit()
        return com.chola.app.data.Resource.Success(isSuccess)
    }

    fun removeFromFavourites(id: String): com.chola.app.data.Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        var set = sharedPref.getStringSet(FAVOURITES_KEY, mutableSetOf<String>())?.toMutableSet() ?: mutableSetOf()
        if (set.contains(id)) {
            set.remove(id)
        }
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        editor.commit()
        editor.putStringSet(FAVOURITES_KEY, set)
        editor.apply()
        val isSuccess = editor.commit()
        return com.chola.app.data.Resource.Success(isSuccess)
    }
}

