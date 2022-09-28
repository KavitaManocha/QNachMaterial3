package com.example.qnachlocal

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.data.DataRepository
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.ui.base.BaseViewModel
import com.example.qnachlocal.utils.NetworkHelper
import com.example.qnachlocal.utils.SingleEvent
import com.example.qnachlocal.utils.wrapEspressoIdlingResource
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel@Inject constructor(
    private val dataRepository: DataRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val userLoginLiveDataPrivate = LiveEvent<Resource<PDFResponse>>()
    val userLoginLiveData: LiveData<Resource<PDFResponse>> get() = userLoginLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    val data:MutableLiveData<User> = MutableLiveData()

    fun genpdf(user: User) {
        viewModelScope.launch {
            userLoginLiveDataPrivate.value = Resource.Loading()
            if (networkHelper.isNetworkConnected()) {
                wrapEspressoIdlingResource {
                    dataRepository.onGenPdf(user).collect {
                        userLoginLiveDataPrivate.value = it
                    }
                }

            } //else userLoginLiveDataPrivate.postValue(Resource.DataError(NO_INTERNET_CONNECTION))
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

//    val loan_id: MutableLiveData<String?> = MutableLiveData()
//    val benef_name: MutableLiveData<String?> = MutableLiveData()
//    val cust_mob: MutableLiveData<String?> = MutableLiveData()
//    val cust_email: MutableLiveData<String?> = MutableLiveData()
//    val ifsc_code: MutableLiveData<String?> = MutableLiveData()
//    val cust_bank: MutableLiveData<String?> = MutableLiveData()
//    val bank_add: MutableLiveData<String?> = MutableLiveData()
//    val cust_acc_no: MutableLiveData<String?> = MutableLiveData()
//    val acc_type: MutableLiveData<String?> = MutableLiveData()
//    val category: MutableLiveData<String?> = MutableLiveData()
//    val frequencyy: MutableLiveData<String?> = MutableLiveData()
//    val ach_amt: MutableLiveData<String?> = MutableLiveData()
//    val mandate_date: MutableLiveData<String?> = MutableLiveData()
//    val start_date: MutableLiveData<String?> = MutableLiveData()
//    val end_date: MutableLiveData<String?> = MutableLiveData()
//    val ref_no: MutableLiveData<String?> = MutableLiveData()
//
}