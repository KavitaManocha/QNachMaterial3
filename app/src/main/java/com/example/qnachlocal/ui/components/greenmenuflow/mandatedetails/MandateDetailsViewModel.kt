package com.example.qnachlocal.ui.components.greenmenuflow.mandatedetails

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.leandroferreira.wizard_forms.contract.WizardPageViewModel
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.data.DataRepository
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.error.NO_INTERNET_CONNECTION
import com.example.qnachlocal.ui.base.BaseViewModel
import com.example.qnachlocal.utils.NetworkHelper
import com.example.qnachlocal.utils.SingleEvent
import com.example.qnachlocal.utils.wrapEspressoIdlingResource
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class MandateDetailsViewModel: WizardPageViewModel<User>() {

    val ach_amt = ObservableField<String>()
    val mandate_date = ObservableField<String>()
    val start_date = ObservableField<String>()
    val end_date = ObservableField<String>()
    val ref_no = ObservableField<String>()

    fun goClick() {
        stateHolder?.stateDto?.ach_amt = ach_amt.get().toString()
        stateHolder?.stateDto?.mandate_date = mandate_date.get().toString()
        stateHolder?.stateDto?.start_date = start_date.get().toString()
        stateHolder?.stateDto?.end_date = end_date.get().toString()
        stateHolder?.stateDto?.ref_no = ref_no.get().toString()
        stateHolder?.notifyStateChange()
        navigator?.nextPage()

    }
}