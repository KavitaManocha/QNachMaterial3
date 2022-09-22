package com.example.qnachlocal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qnachlocal.data.data.dto.User

class SharedViewModel: ViewModel() {

    val data:MutableLiveData<User> = MutableLiveData()

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