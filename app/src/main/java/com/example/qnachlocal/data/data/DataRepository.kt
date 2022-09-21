package com.chola.app.data

import com.chola.app.data.dto.auditTrail.AuditTrailRequest
import com.chola.app.data.dto.linkmandate.LinkMandateRequest
import com.chola.app.data.dto.linkmandate.LinkMandateResponse
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.mandate.MandateRequest
import com.chola.app.data.dto.mandate.MandateResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.chola.app.data.local.LocalData
import com.chola.app.data.remote.RemoteData
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.UserReq
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
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

    override suspend fun doRemoteLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow { emit(remoteRepository.doRemoteLogin(loginRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doRemoteResetPassword(loginRequest: ResetPasswordRequest): Flow<Resource<ResetPasswordResponse>> {
        return flow { emit(remoteRepository.doRemoteResetPassword(loginRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doRemoteMandate(mRequest: MandateRequest): Flow<Resource<MandateResponse>> {
        return flow { emit(remoteRepository.doRemoteMandateResponse(mRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doRemoteReportMandate(mRequest: MandateRequest): Flow<Resource<MandateResponse>> {
        return flow { emit(remoteRepository.doRemoteReportResponse(mRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doRemoteChildMandate(mRequest: MandateRequest): Flow<Resource<MandateResponse>>{
        return flow { emit(remoteRepository.doRemoteChildResponse(mRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doAuditTrialMandate(mRequest: AuditTrailRequest): Flow<Resource<MandateResponse>>{
        return flow { emit(remoteRepository.doAuditTrialResponse(mRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doRemoteLinkMandate(linkMandateRequest: LinkMandateRequest): Flow<Resource<LinkMandateResponse>> {
        return flow { emit(remoteRepository.doRemoteLinkMandate(linkMandateRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doForgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Flow<Resource<ResetPasswordResponse>> {
        return flow { emit(remoteRepository.doForgotPassword(forgotPasswordRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doVerifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<ResetPasswordResponse>> {
        return flow { emit(remoteRepository.doVerifyOtp(verifyOtpRequest)) }.flowOn(ioDispatcher)
    }

    override suspend fun doRemoteSearchMandate(orgId:String ,id:String,query: String): Flow<Resource<MandateResponse>> {
        return flow { emit(remoteRepository.doSearchMandate(orgId, id,query)) }.flowOn(ioDispatcher)

    }


}