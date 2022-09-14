package com.example.qnachlocal

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField

class SpinnerBindingAdapter {
    @BindingAdapter("clicks")
    fun listenClicks(spinner: Spinner, result: ObservableField<String>) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result.set(parent?.getItemAtPosition(position) as String)
            }
        }
    }
}