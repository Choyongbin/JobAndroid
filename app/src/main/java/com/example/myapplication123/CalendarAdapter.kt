package com.example.myapplication123
import android.app.ActionBar
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication123.MainActivity
import com.example.myapplication123.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_schedule.view.*
import java.util.*
import java.util.logging.Handler
import kotlin.coroutines.*
import kotlin.collections.ArrayList

class CalendarAdapter(val mainActivity: MainActivity, val contentdata:ArrayList<CalData>)
    :RecyclerView.Adapter<CalendarAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder:MyViewHolder, view:View, date:String)
    }

    var ItemClickListener:OnItemClickListener?=null
    val baseCalendar = BaseCalendar()
    val thismonth = Calendar.getInstance().get(Calendar.MONTH)
    var arrweatdate = mutableMapOf<String,String>()
    var todayindex:Int

    init{
        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 6){
            todayindex = baseCalendar.prevMonthTailOffset + Calendar.getInstance().get(Calendar.DATE) - 1
        }else{
            todayindex = baseCalendar.prevMonthTailOffset + Calendar.getInstance().get(Calendar.DATE)
        }
        baseCalendar.initBaseCalendar {
            refreshView(it)
        }

    }

    inner class MyViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView), LayoutContainer{
        var datetext: TextView = itemView.findViewById(R.id.tv_date)
        var cont: TextView = itemView.findViewById(R.id.tv_inform)
        init{
            containerView.setOnClickListener{
                ItemClickListener?.OnItemClick(this,it,baseCalendar.datedata[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule,parent,false)

        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {

        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.datetext.setTextColor(Color.parseColor("#467C8F"))

        if(position < baseCalendar.prevMonthTailOffset
            || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate){
            holder.datetext.alpha = 0.4f

        }else{
            holder.datetext.alpha = 1f

        }
        var slicedate = baseCalendar.datedata[position].slice(6..7)
        if(slicedate[0] == '0'){
            holder.datetext.text = slicedate[1].toString()
        }else
            holder.datetext.text = slicedate

        val contarr = getcont(baseCalendar.datedata[position])
        if(contarr.size != 0){
            var contxt = ""
            for(i in 0 until contarr.size){
                contxt+=contarr[i]+"\n"
            }
            holder.datetext.setTextColor(Color.parseColor("#E13697"))
            holder.cont.text = contxt
        }

    }

    fun getcont(date:String):ArrayList<String>{
        val arr = ArrayList<String>()
        for(i in 0 until contentdata.size){
            if(date == contentdata[i].todate){
                arr.add(contentdata[i].content)
            }
        }
        return arr
    }

    fun changeToPrevMonth(){
        baseCalendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth(){
        baseCalendar.changeToNextMonth {
            refreshView(it)
        }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        mainActivity.refreshCurrentMonth(calendar)
    }

}