package com.example.myapplication123

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class selectCompanyAdapter(val items : ArrayList<selectCompanyData>) : RecyclerView.Adapter<selectCompanyAdapter.MyViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick(holder : MyViewHolder, view: View, selectCompanyData: selectCompanyData, position: Int )
    }

    var itemClickListener:OnItemClickListener ?= null

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var companyName: TextView = itemView.findViewById(R.id.companyName)
        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this,it,items[adapterPosition], adapterPosition)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val n = LayoutInflater.from(parent.context).inflate(R.layout.row_selectcompany, parent, false)
        return MyViewHolder(n)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: selectCompanyAdapter.MyViewHolder, position: Int) {
        holder.companyName.text = items[position].comapny
        holder.setIsRecyclable(false)
    }
}