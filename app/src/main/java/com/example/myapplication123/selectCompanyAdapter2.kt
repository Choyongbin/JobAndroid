package com.example.myapplication123

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class selectCompanyAdapter2(val items : ArrayList<selectCompanyData>) : RecyclerView.Adapter<selectCompanyAdapter2.MyViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick(holder : MyViewHolder, view: View, selectCompanyData: selectCompanyData, position: Int )
    }

    var itemClickListener:OnItemClickListener ?= null

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var companyName: TextView = itemView.findViewById(R.id.companyName)
        var startdate : TextView = itemView.findViewById(R.id.startdate)
        var enddate : TextView = itemView.findViewById(R.id.enddate)
        var url : TextView = itemView.findViewById(R.id.url)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this,it,items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val n = LayoutInflater.from(parent.context).inflate(R.layout.row_selectcompany2, parent, false)
        return MyViewHolder(n)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: selectCompanyAdapter2.MyViewHolder, position: Int) {
        holder.companyName.text = items[position].comapny
        holder.url.text = items[position].wantedMobileInfoUrl
        holder.startdate.text = " ~ " + items[position].closeDt

    }

    fun removeItem(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }
}