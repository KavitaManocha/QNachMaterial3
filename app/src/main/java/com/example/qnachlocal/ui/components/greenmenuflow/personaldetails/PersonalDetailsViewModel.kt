package com.example.qnachlocal.ui.components.greenmenuflow.personaldetails

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
class PersonalDetailsViewModel : WizardPageViewModel<User>() {

//    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    val loan_id = ObservableField<String>()
    val benef_name = ObservableField<String>()
    val cust_mob = ObservableField<String>()
    val cust_email = ObservableField<String>()

    fun goClick() {
        stateHolder?.stateDto?.loan_id = loan_id.get()
        stateHolder?.stateDto?.benef_name = benef_name.get()
        if(stateHolder?.stateDto?.cust_mob?.length!! < 10 || stateHolder?.stateDto?.cust_mob?.length!! > 10){
            cust_mob.set("Enter a Valid Phone Number")
        } else{
//            stateHolder?.stateDto?.cust_mob = cust_mob.get()
        }
//        stateHolder?.stateDto?.cust_email = cust_email.get()

        stateHolder?.notifyStateChange()
        navigator?.nextPage()
    }


}