package com.example.qnachlocal.ui.components.greenmenuflow.accountdetails

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
import com.example.qnachlocal.data.error.NO_INTERNET_CONNECTION
import com.example.qnachlocal.ui.base.BaseViewModel
import com.example.qnachlocal.utils.NetworkHelper
import com.example.qnachlocal.utils.SingleEvent
import com.example.qnachlocal.utils.wrapEspressoIdlingResource
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountDetailsViewModel : WizardPageViewModel<User>() {

    val ifsc_code = ObservableField<String>()
    val cust_bank = ObservableField<String>()
    val cust_bank_add = ObservableField<String>()
    val cust_acc_no = ObservableField<String>()
    var cust_acc_type = ObservableField<String>()
    //        .apply {
//        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
//            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                Log.d("value", this@apply.get()!!) //selected value
//            }
//        })
//    }
    val custt_acc_type = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            cust_acc_type = parent?.getItemAtPosition(position) as ObservableField<String>
        }
    }
    var category = ObservableField<String>()
    val categorry = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            category = parent?.getItemAtPosition(position) as ObservableField<String>
        }
    }

    var frequency = ObservableField<String>()
    val freqquency = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            frequency = parent?.getItemAtPosition(position) as ObservableField<String>
        }
    }

    fun goClick() {
        stateHolder?.stateDto?.ifsc_code = ifsc_code.get()
        stateHolder?.stateDto?.cust_bank = cust_bank.get()
        stateHolder?.stateDto?.cust_bank_add = cust_bank_add.get()
        stateHolder?.stateDto?.cust_acc_no = cust_acc_no.get()
        stateHolder?.stateDto?.cust_acc_type = cust_acc_type.get().toString()
//        stateHolder?.stateDto?.category = category.get().toString()
//        stateHolder?.stateDto?.frequency = frequency.get().toString()
        stateHolder?.notifyStateChange()
        navigator?.nextPage()
    }
}