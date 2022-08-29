package com.ugro.app.data.remote.service

import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Sunil
 */

interface UgroApiService {

    @POST("NScanApp/login")
    suspend fun login(
        @Body lRequest: LoginRequest
    ): Response<LoginResponse>
}


