package com.example.qnachlocal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterGreen : RecyclerView.Adapter<CustomAdapterGreen.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowlayout_green, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 2
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }



}

