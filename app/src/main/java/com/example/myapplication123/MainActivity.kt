package com.example.myapplication123

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication123.databinding.ActivityMainBinding
import com.example.myapplication123.databinding.ContentMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_schedule.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ContentMainBinding
    private lateinit var today:String
    val weather = ArrayList<CalData>()
    var caldata = ArrayList<CalData>()
    var companyList = ArrayList<selectCompanyData>()
    var companyNameList = ArrayList<String>()
    var companyDateList = ArrayList<String>()
    private lateinit var cal: Calendar
    private lateinit var calAdapter: CalendarAdapter
    lateinit var adapter:selectCompanyAdapter2
    private var userID:String = ""
    private var userPW:String= ""
    private var userNAME:String= ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("userID")){
            userID = intent.getStringExtra("userID").toString()
        }
        if(intent.hasExtra("userName")){
            userNAME = intent.getStringExtra("userName").toString()
        }
        if(intent.hasExtra("userPass")){
            userPW = intent.getStringExtra("userPass").toString()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        binding2 = ContentMainBinding.inflate(layoutInflater)

        val slidePanel = binding2.mainFrame                      // SlidingUpPanel
        slidePanel.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가

        var navigationView:NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var header : View = navigationView.getHeaderView(0)

        var useridView:TextView = header.findViewById(R.id.useridView)
        var usernameView:TextView = header.findViewById(R.id.usernameView)

        useridView.text = userID
        usernameView.text = userNAME


        val window: Window = this@MainActivity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
        nav_view.setNavigationItemSelectedListener(this)


        init()

        cal = Calendar.getInstance()
        recyclerView.layoutManager = GridLayoutManager(this, BaseCalendar.DAYS_OF_WEEK)
        calAdapter = CalendarAdapter(this, caldata)
        calAdapter.ItemClickListener=object: CalendarAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: CalendarAdapter.MyViewHolder,
                view: View,
                date:String
            ) {
                Log.d("bblank", view.tv_inform.text.isNotBlank().toString())
                if(view.tv_inform.text.isNotBlank()){
                    slidePanel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
                }
            }
        }

        tv_prev_month.setOnClickListener{
            calAdapter.changeToPrevMonth()
        }

        tv_next_month.setOnClickListener{
            calAdapter.changeToNextMonth()
        }

        recyclerView.adapter = calAdapter
        Log.d("userID", userID)
        Log.d("userName", userNAME)
        Log.d("userPW", userPW)

        fab.setOnClickListener { view ->
            val i = Intent(this, MainActivity3::class.java )
            i.putExtra("userName",userNAME)
            i.putExtra("userID", userID)
            i.putExtra("userPass", userPW)
            startActivity(i)
        }

        sidebar.setOnClickListener{ view ->
            drawer_layout.openDrawer((GravityCompat.START))
        }


    }

    fun refreshCalendar(){

    }

    fun init(){
        val selectCompanyList = findViewById<RecyclerView>(R.id.recyclerView2)

        if(intent.hasExtra("companyList")){
            companyList = intent.getSerializableExtra("companyList") as ArrayList<selectCompanyData>
        }

        for(i in companyList.indices){
            var closeDtArray = companyList[i].closeDt
            closeDtArray = closeDtArray.replace("채용시까지", "")
            closeDtArray = closeDtArray.replace("-","")
            closeDtArray = closeDtArray.replace(" ","")
            closeDtArray = "20$closeDtArray"
            val cd = CalData(closeDtArray, companyList[i].comapny)
            caldata.add(cd)

        }


        adapter = selectCompanyAdapter2(ArrayList<selectCompanyData>())
        adapter.itemClickListener = object:selectCompanyAdapter2.OnItemClickListener{
            override fun OnItemClick(
                holder: selectCompanyAdapter2.MyViewHolder,
                view: View,
                selectCompanyData: selectCompanyData,
                position: Int
            ) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectCompanyData.wantedMobileInfoUrl))
                intent.putExtra("userName",userNAME)
                intent.putExtra("userID", userID)
                intent.putExtra("userPass", userPW)
                startActivity(intent)
            }

        }
        for(i in companyList.indices){
            adapter.items.add(selectCompanyData(companyList[i].comapny,companyList[i].title,companyList[i].salTpNm,companyList[i].sal,companyList[i].region,companyList[i].holidayTpNm,companyList[i].minEdubg,companyList[i].career,companyList[i].regDt,companyList[i].closeDt,companyList[i].wantedMobileInfoUrl,companyList[i].basicAddr))
        }
        selectCompanyList.adapter = adapter
        val simpleCallback = object:ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView2)
    }

    fun refreshCurrentMonth(calendar:Calendar){
        val sdf = SimpleDateFormat("yyyy MM", Locale.KOREAN)
        tv_current_month.text = sdf.format(calendar.time)
    }


    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // 패널이 슬라이드 중일 때
        override fun onPanelSlide(panel: View?, slideOffset: Float) {

        }
        // 패널의 상태가 변했을 때
        override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_account-> {
                val i = Intent(this, ChangeAccount::class.java)
                i.putExtra("userName",userNAME)
                i.putExtra("userID", userID)
                i.putExtra("userPass", userPW)
                startActivity(i)
            }
            R.id.nav_password->{
                val i = Intent(this, ChangePW::class.java)
                i.putExtra("userPw", userPW)
                i.putExtra("userID", userID)
                i.putExtra("userPass", userPW)
                startActivity(i)
            }
        }

        return false
    }


}