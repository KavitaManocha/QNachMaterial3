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
    @POST("encrypt")
    suspend fun testDecrypt(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}
