package com.example.myapplication123

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.CheckedTextView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.row_selectcompany.view.*
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.lang.ref.WeakReference
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class selectCompany : AppCompatActivity() {
    lateinit var adapter:selectCompanyAdapter
    lateinit var arrlist:ArrayList<Boolean>

    private var userID:String = ""
    private var userPW:String= ""
    private var userNAME:String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_company)

        setSupportActionBar(findViewById(R.id.toolbar))
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        init()
        startTask()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun init(){
        if(intent.hasExtra("userID")){
            userID = intent.getStringExtra("userID").toString()
        }
        if(intent.hasExtra("userName")){
            userNAME = intent.getStringExtra("userName").toString()
        }
        if(intent.hasExtra("userPass")){
            userPW = intent.getStringExtra("userPass").toString()
        }

        Log.d("userID", userID)
        Log.d("userName", userNAME)
        Log.d("userPW", userPW)

        var companyList = findViewById<RecyclerView>(R.id.recyclerViewHire)
        var continuBtn = findViewById<AppCompatButton>(R.id.continueBtn)
        var selectCompanyNum = arrayListOf<Int>()
        var selectCompanyList = arrayListOf<selectCompanyData>()
        companyList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = selectCompanyAdapter(ArrayList<selectCompanyData>())
        adapter.itemClickListener = object:selectCompanyAdapter.OnItemClickListener{

            override fun OnItemClick(
                holder: selectCompanyAdapter.MyViewHolder,
                view: View,
                selectCompanyData: selectCompanyData,
                position: Int
            ) {

                if(selectCompanyNum.contains(position)){
                    selectCompanyNum.remove(position)
                    selectCompanyList.remove(selectCompanyData)
                    view.companyName.setBackgroundResource(R.drawable.btndefault2)
                    view.companyName.isChecked = false
                    //view.companyName.setTextColor(R.color.selector_text4)
                    for(i in selectCompanyNum.indices)
                        Log.d("list", selectCompanyNum[i].toString())
                }
                else{
                    selectCompanyNum.add(position)
                    selectCompanyList.add(selectCompanyData)
                    view.companyName.setBackgroundResource(R.drawable.btnactivated2)
                    view.companyName.isChecked = true
                    //view.companyName.setTextColor(R.color.white)
                    for(i in selectCompanyNum.indices)
                        Log.d("list", selectCompanyNum[i].toString())
                }
                if(selectCompanyNum.isEmpty()){
                    continuBtn.isEnabled= false
                    continuBtn.isClickable = false
                }
                else{
                    continuBtn.isClickable = true
                    continuBtn.isEnabled = true
                }


            }
        }
        companyList.adapter = adapter

        continuBtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("companyList", selectCompanyList)
            i.putExtra("userName",userNAME)
            i.putExtra("userID", userID)
            i.putExtra("userPass", userPW)
            val resposeListener: Any = object : Response.Listener<String?> {
                override fun onResponse(response: String?) {
                    try {
                        val jsonObject = JSONObject(response)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            for(i in selectCompanyList.indices){
                val addCompanyRequest = AddCompanyRequest(userID, selectCompanyList[i].comapny, selectCompanyList[i].closeDt, selectCompanyList[i].wantedMobileInfoUrl,resposeListener)
                val queue: RequestQueue = Volley.newRequestQueue(this@selectCompany)
                queue.add(addCompanyRequest)
            }
            startActivity(i)
        }
    }



    fun startTask(){
        var regionCode:String = ""
        var jobCode :String = ""
        val task = MyAsyncTask(this)
        if(intent.hasExtra("regionCode")){
            regionCode = intent.getStringExtra("regionCode").toString()
        }
        if(intent.hasExtra("jobCode")){
            jobCode = intent.getStringExtra("jobCode").toString()
        }
        Log.d("dd",jobCode)
        Log.d("ddd",regionCode)
//        var dd:TextView = findViewById(R.id.text1)
//        var ddd:TextView = findViewById(R.id.text2)
//        dd.setText(regionCode)
//        ddd.setText(jobCode)
        //print(jobCode)
        //print(regionCode)
        task.execute(URL("http://openapi.work.go.kr/opi/opi/opia/wantedApi.do?authKey=WNKAXMT4LJMJ2QG4SCBHK2VR1HJ&callTp=L&returnType=XML&startPage=1&display=100&occupation=$jobCode&region=$regionCode"))
        //task.execute(URL("http://openapi.work.go.kr/opi/opi/opia/wantedApi.do?authKey=WNKAXMT4LJMJ2QG4SCBHK2VR1HJ&callTp=L&returnType=XML&startPage=1&display=100"))
    }

    class MyAsyncTask(context:selectCompany) : AsyncTask<URL, Unit, Unit>(){
        val activityReference = WeakReference(context)

        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg params: URL?): Unit {

            val activity: selectCompany? = activityReference.get()
            activity?.adapter?.items?.clear()

            val xml: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(params[0].toString())
            xml.documentElement.normalize()

            val list: NodeList = xml.getElementsByTagName("wanted")

            for (i in 0 until list.length) {
                val n: Node = list.item(i)
                if (n.nodeType == Node.ELEMENT_NODE) {
                    val elem = n as Element
                    val map = mutableMapOf<String, String>()

                    for (j in 0 until elem.attributes.length) {
                        map.putIfAbsent(
                            elem.attributes.item(j).nodeName,
                            elem.attributes.item(j).nodeValue
                        )
                    }
                    val company = elem.getElementsByTagName("company").item(0).textContent
                    val title = elem.getElementsByTagName("title").item(0).textContent
                    val salTpNm = elem.getElementsByTagName("salTpNm").item(0).textContent
                    val sal = elem.getElementsByTagName("sal").item(0).textContent
                    val holidayTpNm = elem.getElementsByTagName("holidayTpNm").item(0).textContent
                    val minEdubg = elem.getElementsByTagName("minEdubg").item(0).textContent
                    val career = elem.getElementsByTagName("career").item(0).textContent
                    val regDt = elem.getElementsByTagName("regDt").item(0).textContent
                    val closeDt = elem.getElementsByTagName("closeDt").item(0).textContent
                    val region = elem.getElementsByTagName("region").item(0).textContent
                    val wantedMobileInfoUrl = elem.getElementsByTagName("wantedMobileInfoUrl").item(0).textContent
                    val basicAddr = elem.getElementsByTagName("basicAddr").item(0).textContent
                    Log.d("compnay",company)
                    activity?.adapter?.items?.add(
                        selectCompanyData(
                            company,
                            title,
                            salTpNm,
                            sal,
                            region,
                            holidayTpNm,
                            minEdubg,
                            career,
                            regDt,
                            closeDt,
                            wantedMobileInfoUrl,
                            basicAddr
                        )
                    )
                }
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            val activity = activityReference.get()
            if(activity == null || activity.isFinishing)
                return
            activity.adapter.notifyDataSetChanged()
        }

    }
}