package com.example.qnachlocal.ui.components.dashboard.reports

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qnachlocal.CustomAdapterGreen
import com.example.qnachlocal.R
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.REquestReport
import com.example.qnachlocal.data.data.dto.ReportResponse

class ReportsAdapter(var listItems: ArrayList<ReportResponse>) : RecyclerView.Adapter<ReportsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pdf_report, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }



}

