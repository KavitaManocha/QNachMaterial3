package com.example.qnachlocal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.qnachlocal.data.data.dto.RecyclerviewItem

class CustomAdapterGreen(private var menuItems: ArrayList<RecyclerviewItem>): RecyclerView.Adapter<CustomAdapterGreen.ViewHolder>(){

    var onItemClick:((RecyclerviewItem) -> Unit)?= null

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var backgroundd: CardView= itemView.findViewById(R.id.cv_green)
        var icon: ImageView = itemView.findViewById(R.id.iv_icon)
        var task: TextView = itemView.findViewById(R.id.tv_task)
        var proceed: ImageView = itemView.findViewById(R.id.iv_forward)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowlayout_green, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
var menu = menuItems[position]
        holder.backgroundd.setCardBackgroundColor(menu.background)
        holder.icon.setImageResource(menu.desc_image)
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

//class CustomAdapterGreen(private ) : RecyclerView.Adapter<CustomAdapterGreen.ViewHolder>() {
//
////    private var mListener: onItemClickListener?= null
////
////    interface  onItemClickListener{
////
////        fun onItemClick(position: Int)
////    }
////
////    fun setOnItemClickListener(listener: onItemClickListener){
////        mListener = listener
////    }
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
////        val view = LayoutInflater.from(parent.context)
////            .inflate(R.layout.rowlayout_green, parent, false)
////
////        return ViewHolder(view,mListener!!)
////    }
////
////    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
////
////    }
////
////    override fun getItemCount(): Int {
////        return 2
////    }
////
////    class ViewHolder(itemView: View, listener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
////val cardView: CardView = itemView.findViewById(R.id.cv_green)
////
////        init {
////            itemView.setOnClickListener {
////                listener.onItemClick(adapterPosition)
////            }
////        }
////    }
//
//
//
//}

