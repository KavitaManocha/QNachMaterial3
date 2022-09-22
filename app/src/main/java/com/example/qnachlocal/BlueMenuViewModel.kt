package com.example.qnachlocal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qnachlocal.data.data.dto.Users


class BlueMenuViewModel : ViewModel(){

    val dataa:MutableLiveData<Users> = MutableLiveData()

}