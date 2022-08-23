package com.example.qnachlocal.data.remote

import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.data.Resource

/**
 * Created by Sunil
 */

internal interface RemoteDataSource {
    suspend fun doRemoteDecrypt(
        loginRequest: LoginRequest
    ): Resource<LoginResponse>
}
