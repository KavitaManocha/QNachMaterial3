package com.example.qnachlocal.data.data.dto

import android.util.Log

data class Users(
    var loan_id: String?,
    var cust_mob: String?,
    var cust_email: String?,
    var acc_holder: String?,
    var cust_bank: String?,
    var cust_acc_no: String?,
    var conf_acc_no: String?,
    var category: String?,
    var frequency: String?,
    var ach_amt: String?,
    var start_date: String?,
    var end_date: String?
) {

    fun onDateChanged(year: Int, month: Int, day: Int) {
        Log.v("Test_Picker", "$year $month $day")
    }

//    cust_acc_type = ObservableField<String>().apply {
//        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
//            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                Log.d("value",this@apply.get()) //selected value
//            }
//        })
//    }
}