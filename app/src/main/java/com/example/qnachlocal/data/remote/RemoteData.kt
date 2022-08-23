package com.example.qnachlocal.data.remote

import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.error.NETWORK_ERROR
import com.example.qnachlocal.data.error.NO_INTERNET_CONNECTION
import com.example.qnachlocal.utils.NetworkConnectivity
import com.ugro.app.data.remote.service.UgroApiService
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
        val response = decryptData.testDecrypt(loginRequest)
        return when (val response1 = processCall(response)) {
            is LoginResponse -> {
                Resource.Success(data = response1)
            }
            else -> {
                Resource.DataError(errorCode = response1 as Int)
            }
        }
    }


}
