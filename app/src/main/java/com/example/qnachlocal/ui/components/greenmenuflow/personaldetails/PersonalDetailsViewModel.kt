package com.example.qnachlocal.ui.components.greenmenuflow.personaldetails

import androidx.databinding.ObservableField
import br.com.leandroferreira.wizard_forms.contract.WizardPageViewModel
import com.example.qnachlocal.data.data.dto.User

//@HiltViewModel
class PersonalDetailsViewModel : WizardPageViewModel<User>() {

//    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    val loan_id = ObservableField<String>()
    val benef_name = ObservableField<String>()
    val cust_mob = ObservableField<String>()
    val cust_email = ObservableField<String>()

    fun goClick() {

        println("===========test====="+stateHolder?.stateDto?.loan_id)

        if(stateHolder?.stateDto?.loan_id?.isNullOrBlank() == true){
            loan_id.set("Enter Loan Id")
        } else{
            stateHolder?.stateDto?.loan_id = loan_id.get()
        }

        if(stateHolder?.stateDto?.benef_name?.isNullOrBlank() == true){
            loan_id.set("Enter Beneficiary Name")
        } else{
            stateHolder?.stateDto?.benef_name = benef_name.get()
        }

        if(stateHolder?.stateDto?.cust_mob?.length!! < 10 || stateHolder?.stateDto?.cust_mob?.length!! > 10){
            cust_mob.set("Enter a Valid Phone Number")
        } else if (stateHolder?.stateDto?.cust_mob?.isNullOrBlank() == true){
            cust_mob.set("Enter Phone Number")
        }
        else{
            stateHolder?.stateDto?.cust_mob = cust_mob.get().toString()
        }

        if(stateHolder?.stateDto?.cust_email?.isNullOrBlank() == true){
            loan_id.set("Enter Beneficiary Name")
        } else{
            stateHolder?.stateDto?.cust_email = cust_email.get().toString()
        }

//        stateHolder?.stateDto?.cust_email = cust_email.get()

        stateHolder?.notifyStateChange()
        navigator?.nextPage()
    }


}