package com.example.myapplication123

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CompanyAdapter(val mainActivity: MainActivity,val items : ArrayList<selectCompanyData> )
    :RecyclerView.Adapter<CalendarAdapter.MyViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(holder : MyViewHolder, view: View, selectCompanyData: selectCompanyData, position: Int )
    }

    var itemClickListener:OnItemClickListener ?= null

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var company: TextView = itemView.findViewById(R.id.companyName)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this,it,items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarAdapter.MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CalendarAdapter.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        mainActivity.refreshCurrentMonth(calendar)
    }


}