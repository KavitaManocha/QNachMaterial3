package com.example.qnachlocal.ui.components.bluemenuflow.customerdetails

import android.view.View
import android.widget.AdapterView
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
import com.example.qnachlocal.data.data.dto.Users
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
class CustomerDetailsViewModel : WizardPageViewModel<Users>() {

    val loan_id = ObservableField<String>()
    val cust_mob = ObservableField<String>()
    val cust_email = ObservableField<String>()
    val ach_amt = ObservableField<String>()
    var start_date = ObservableField<String>()
    var end_date = ObservableField<String>()
    //        .apply {
//        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
//            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                Log.d("value", this@apply.get()!!) //selected value
//            }
//        })
//    }

    fun goClick() {
        stateHolder?.stateDto?.loan_id = loan_id.get()
        stateHolder?.stateDto?.cust_mob = cust_mob.get()
        stateHolder?.stateDto?.cust_email = cust_email.get()
        stateHolder?.stateDto?.ach_amt = ach_amt.get()
        stateHolder?.stateDto?.start_date = start_date.get().toString()
        stateHolder?.stateDto?.end_date = end_date.get().toString()
//        stateHolder?.stateDto?.category = category.get().toString()
//        stateHolder?.stateDto?.frequency = frequency.get().toString()
        stateHolder?.notifyStateChange()
        navigator?.nextPage()
    }
}