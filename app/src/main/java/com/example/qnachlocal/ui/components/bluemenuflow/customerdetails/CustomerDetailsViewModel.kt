package com.example.qnachlocal.ui.components.bluemenuflow.customerdetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.data.DataRepository
import com.example.qnachlocal.data.Resource
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
class CustomerDetailsViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val userLoginLiveDataPrivate = LiveEvent<Resource<LoginResponse>>()
    val userLoginLiveData: LiveData<Resource<LoginResponse>> get() = userLoginLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            userLoginLiveDataPrivate.value = Resource.Loading()
            if (networkHelper.isNetworkConnected()) {
                wrapEspressoIdlingResource {
                    dataRepository.doRemoteDecrypt(loginRequest).collect {
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