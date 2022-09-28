package com.example.qnachlocal.data

import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sunil
 */

interface DataRepositorySource {
    suspend fun doRemoteDecrypt(
        loginRequest: LoginRequest
    ): Flow<Resource<LoginResponse>>

    suspend fun onRemoteForgotPassword(loginRequest:ForgotPasswordRequest): Flow<Resource<ResetPasswordResponse>>

    suspend fun onRemoteVerifyOtp(loginRequest:VerifyOtpRequest): Flow<Resource<ResetPasswordResponse>>

    suspend fun onGenPdf(user: User): Flow<Resource<PDFResponse>>
}
