package com.example.qnachlocal.data.remote

import com.chola.app.data.dto.auditTrail.AuditTrailRequest
import com.chola.app.data.dto.linkmandate.LinkMandateRequest
import com.chola.app.data.dto.linkmandate.LinkMandateResponse
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.mandate.MandateRequest
import com.chola.app.data.dto.mandate.MandateResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.Usser
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.REquestReport
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
import com.example.qnachlocal.data.error.NETWORK_ERROR
import com.example.qnachlocal.data.error.NO_INTERNET_CONNECTION
import com.example.qnachlocal.data.remote.service.UgroApiService
import com.example.qnachlocal.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Sunil
 */

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) :
    RemoteDataSource {

    private fun processCall(response: Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    override suspend fun doRemoteDecrypt(loginRequest: LoginRequest): Resource<LoginResponse> {
        val decryptData = serviceGenerator.createService(UgroApiService::class.java)
        val response = decryptData.login(loginRequest)
        return when (val response1 = processCall(response)) {
            is LoginResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = response1 as Int)
            }
        }
    }

    override suspend fun doForgotPassword(loginRequest: ForgotPasswordRequest): Resource<ResetPasswordResponse> {
        val decryptData = serviceGenerator.createService(UgroApiService::class.java)
        val response = decryptData.forgotPassword(loginRequest)
        return when (val response1 = processCall(response)) {
            is ResetPasswordResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = response1 as Int)
            }
        }
    }

    override suspend fun doVerifyOtp(loginRequest: VerifyOtpRequest): Resource<ResetPasswordResponse> {
        val decryptData = serviceGenerator.createService(UgroApiService::class.java)
        val response = decryptData.verifyOtp(loginRequest)
        return when (val response1 = processCall(response)) {
            is ResetPasswordResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = response1 as Int)
            }
        }
    }

    override suspend fun doGeneratePdf(user: Usser): Resource<PDFResponse> {
        val decryptData = serviceGenerator.createService(UgroApiService::class.java)
        val response = decryptData.createPDF(user)
        return when (val response1 = processCall(response)) {
            is PDFResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = response1 as Int)
            }
        }
    }

    override suspend fun doRequestReport(requestReport: REquestReport): Resource<PDFResponse> {
        val decryptData = serviceGenerator.createService(UgroApiService::class.java)
        val response = decryptData.viewReport(requestReport)
        return when (val response1 = processCall(response)) {
            is PDFResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = response1 as Int)
            }
        }
    }


}
