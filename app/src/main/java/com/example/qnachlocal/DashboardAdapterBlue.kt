package com.example.qnachlocal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.qnachlocal.data.data.dto.DashboardItem

class DashboardAdapterBlue (private var menuItems: ArrayList<DashboardItem>): RecyclerView.Adapter<DashboardAdapterBlue.ViewHolder>(){

    var onItemClick:((DashboardItem) -> Unit)?= null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var backgroundd: CardView = itemView.findViewById(R.id.cv_green)
        var icon: TextView = itemView.findViewById(R.id.tv_count)
        var task: TextView = itemView.findViewById(R.id.tv_task)
        var proceed: ImageView = itemView.findViewById(R.id.iv_forward)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_dashboard_blue, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var menu = menuItems[position]
        holder.backgroundd.setCardBackgroundColor(menu.background)
        holder.icon.setText(menu.total_count)
        holder.task.setText(menu.task)
        holder.proceed.setImageResource(menu.forward_image)

        holder.backgroundd.setOnClickListener {
            onItemClick?.invoke(menu)
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

}