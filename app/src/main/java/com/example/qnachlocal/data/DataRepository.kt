package com.example.qnachlocal.data


import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
import com.example.qnachlocal.data.local.LocalData
import com.example.qnachlocal.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Sunil
 */

class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val localRepository: LocalData, private val ioDispatcher: CoroutineContext) :
    DataRepositorySource {

    override suspend fun doRemoteDecrypt(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow { emit(remoteRepository.doRemoteDecrypt(loginRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun onRemoteForgotPassword(loginRequest: ForgotPasswordRequest): Flow<Resource<ResetPasswordResponse>> {
        return flow { emit(remoteRepository.doForgotPassword(loginRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun onRemoteVerifyOtp(loginRequest: VerifyOtpRequest): Flow<Resource<ResetPasswordResponse>> {
        return flow { emit(remoteRepository.doVerifyOtp(loginRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun onGenPdf(user: User): Flow<Resource<PDFResponse>> {
        return flow { emit(remoteRepository.doGeneratePdf(user)) }.flowOn(ioDispatcher)
    }

}
