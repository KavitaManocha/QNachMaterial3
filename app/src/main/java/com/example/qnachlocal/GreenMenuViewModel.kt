package com.example.qnachlocal

import androidx.databinding.ObservableArrayList
import br.com.leandroferreira.wizard_forms.contract.WizardNavigator
import br.com.leandroferreira.wizard_forms.contract.WizardPageViewModel
import br.com.leandroferreira.wizard_forms.contract.WizardStateHolder
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.ui.components.greenmenuflow.accountdetails.AccountDetailsViewModel
//import com.example.qnachlocal.ui.components.greenmenuflow.mandatedetails.MandateDetailsViewModel
//import com.example.qnachlocal.ui.components.greenmenuflow.personaldetails.PersonalDetailsViewModel
import me.tatarka.bindingcollectionadapter2.OnItemBind

//class GreenMenuViewModel(val navigator: WizardNavigator) {

//    val itemBinding: OnItemBind<WizardPageViewModel<User>>
//        get() = OnItemBind { itemBinding1, position, _ ->
//            when (position) {
//                0 -> itemBinding1.set(BR.viewModel, R.layout.fragment_personal_details)
//                1 -> itemBinding1.set(BR.viewModel, R.layout.fragment_account_details)
//                2 -> itemBinding1.set(BR.viewModel, R.layout.fragment_mandate_details)
//                else -> throw IllegalArgumentException("There is more viewModel than views!")
//
//            }
//        }
//
//    val pages = ObservableArrayList<WizardPageViewModel<User>>()
//    val stateHolder = WizardStateHolder<User>(User("", "", "","","","",
//        "","","","","","","","","",""))
//
//    init {
//        pages.add(PersonalDetailsViewModel())
//        pages.add(AccountDetailsViewModel())
//        pages.add(MandateDetailsViewModel())
//
//    }
//}