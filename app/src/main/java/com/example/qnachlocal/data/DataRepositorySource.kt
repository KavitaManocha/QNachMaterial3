package com.example.qnachlocal.data

import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sunil
 */

interface DataRepositorySource {
    suspend fun doRemoteDecrypt(
        loginRequest: LoginRequest
    ): Flow<Resource<LoginResponse>>


}
