package com.chola.app.data.remote

import com.chola.app.data.Resource
import com.chola.app.data.dto.auditTrail.AuditTrailRequest
import com.chola.app.data.dto.linkmandate.LinkMandateRequest
import com.chola.app.data.dto.linkmandate.LinkMandateResponse
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.mandate.MandateRequest
import com.chola.app.data.dto.mandate.MandateResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.chola.app.data.error.NETWORK_ERROR
import com.chola.app.data.error.NO_INTERNET_CONNECTION
import com.chola.app.data.remote.service.CholaApiService
import com.example.qnachlocal.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Sunil
 */

class RemoteData @Inject
constructor(serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) :
    RemoteDataSource {
    private val serviceApi = serviceGenerator.createService(CholaApiService::class.java)

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

    override suspend fun doRemoteLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        val response = serviceApi.login(loginRequest)
        return when (val response1 = processCall(response)) {
            is LoginResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }

    override suspend fun doRemoteResetPassword(loginRequest: ResetPasswordRequest): Resource<ResetPasswordResponse> {
        val response = serviceApi.resetPassword(loginRequest)
        return when (val response1 = processCall(response)) {
            is ResetPasswordResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }

    override suspend fun doRemoteMandateResponse(mandate: MandateRequest): Resource<MandateResponse> {
        val response = serviceApi.mandateList(mandate)
        return when (val response1 = processCall(response)) {
            is MandateResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }

    override suspend fun doRemoteReportResponse(mandate: MandateRequest): Resource<MandateResponse> {
        val response = serviceApi.reportList(mandate)
        return when (val response1 = processCall(response)) {
            is MandateResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }

    override suspend fun doRemoteChildResponse(mandate: MandateRequest): Resource<MandateResponse> {
        val response = serviceApi.childMandateList(mandate)
        return when (val response1 = processCall(response)) {
            is MandateResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }

    override suspend fun doAuditTrialResponse(mandate: AuditTrailRequest): Resource<MandateResponse> {
        val response = serviceApi.auditTrialList(mandate)
        return when (val response1 = processCall(response)) {
            is MandateResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }

    override suspend fun doRemoteLinkMandate(mandate: LinkMandateRequest): Resource<LinkMandateResponse> {
        val response = serviceApi.mandateLink(mandate)
        return when (val response1 = processCall(response)) {
            is LinkMandateResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }

    override suspend fun doSearchMandate( id:String,mandate: String): Resource<MandateResponse> {
        val response = serviceApi.searchMandate(id,mandate)
        return when (val response1 = processCall(response)) {
            is MandateResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = 1 as Int)
            }
        }
    }


}