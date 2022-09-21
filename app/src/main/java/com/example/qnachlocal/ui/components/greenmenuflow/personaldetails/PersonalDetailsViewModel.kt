package com.example.qnachlocal.ui.components.greenmenuflow.personaldetails

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.leandroferreira.wizard_forms.contract.WizardPageViewModel
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.R
import com.example.qnachlocal.data.DataRepository
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.UserReq
import com.example.qnachlocal.ui.base.BaseViewModel
import com.example.qnachlocal.utils.NetworkHelper
import com.example.qnachlocal.utils.SingleEvent
import com.example.qnachlocal.utils.wrapEspressoIdlingResource
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class PersonalDetailsViewModel @Inject constructor(
//    private val dataRepository: DataRepository,
//    private val networkHelper: NetworkHelper
//) : BaseViewModel() {
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    val userLoginLiveDataPrivate = LiveEvent<Resource<User>>()
//    val userLoginLiveData: LiveData<Resource<User>> get() = userLoginLiveDataPrivate
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
//    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
//    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate
//
//
//    fun showToastMessage(errorCode: Int) {
//        val error = errorManager.getError(errorCode)
//        showToastPrivate.value = SingleEvent(error.description)
//    }
//
//    fun loginUserDaata(user: UserReq) {
//        if(user.loan_id.equals("")){
//            showToastPrivate.value = SingleEvent(R.string.enter_loan_id)
//        }
//        else if (user.benef_name.equals("")){
//            showToastPrivate.value = SingleEvent(R.string.enter_benef_name)
//        }
//        else if (user.cust_mob.equals("")){
//            showToastPrivate.value = SingleEvent(R.string.enter_mobile_no)
//        }
//        else if (user.cust_mob.length<10|| user.cust_mob.length>10){
//            showToastPrivate.value = SingleEvent(R.string.enter_valid_mobile_number)
//        }
//        else if (user.cust_email.equals("")){
//            showToastPrivate.value = SingleEvent(R.string.enter_email_id)
//        }
//        else {
//
//        }
//
//    }
//}