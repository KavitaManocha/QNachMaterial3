package com.example.qnachlocal.ui.components.dashboard.reports

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.data.DataRepository
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.REquestReport
import com.example.qnachlocal.data.data.dto.ReportResponse
import com.example.qnachlocal.data.error.NO_INTERNET_CONNECTION
import com.example.qnachlocal.ui.base.BaseViewModel
import com.example.qnachlocal.utils.NetworkHelper
import com.example.qnachlocal.utils.SingleEvent
import com.example.qnachlocal.utils.wrapEspressoIdlingResource
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val userLoginLiveDataPrivate = LiveEvent<Resource<ReportResponse>>()
    val userLoginLiveData: LiveData<Resource<ReportResponse>> get() = userLoginLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun loginUser(loginRequest: REquestReport) {
        viewModelScope.launch {
            userLoginLiveDataPrivate.value = Resource.Loading()
            if (networkHelper.isNetworkConnected()) {
                wrapEspressoIdlingResource {
                    dataRepository.onRequestReport(loginRequest).collect {
                        userLoginLiveDataPrivate.value = it
                    }
                }

            } else userLoginLiveDataPrivate.postValue(Resource.DataError(NO_INTERNET_CONNECTION))
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}