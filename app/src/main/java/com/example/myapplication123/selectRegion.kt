package com.example.myapplication123

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class selectRegion : AppCompatActivity() {

    var list_of_top =  arrayOf("지역분류","서울","부산","대구","인천","광주","대전","울산","경기","강원","충북","충남","전북","전남","경북","경남","제주")
    var regionCode: String = "11000"
    private var userID:String = ""
    private var userPW:String= ""
    private var userNAME:String= ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_region)

        setSupportActionBar(findViewById(R.id.toolbar))
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        initFilter()
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            android.R.id.home->{
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
        var continueBtn = findViewById<Button>(R.id.continueBtn)

        var jobCode:String = ""
        if(intent.hasExtra("jobCode")){
               jobCode = intent.getStringExtra("jobCode").toString()
        }
        //Log.d("dd",jobCode)
        //Log.d("ddd",regionCode)
        //print(jobCode)
        //print(regionCode)

        Log.d("userID", userID)
        Log.d("userName", userNAME)
        Log.d("userPW", userPW)
        continueBtn.setOnClickListener{
            val i = Intent(this,selectCompany::class.java)
            i.putExtra("regionCode", regionCode)
            i.putExtra("jobCode", jobCode)
            i.putExtra("userName",userNAME)
            i.putExtra("userID", userID)
            i.putExtra("userPass", userPW)
            startActivity(i)
        }
    }

    fun initFilter(){
        var regionSpinner = findViewById<Spinner>(R.id.regionSpinner)
        var continueBtn = findViewById<Button>(R.id.continueBtn)
        regionSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list_of_top)
        regionSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->regionCode="00000"
                    1->regionCode="11000"
                    2->regionCode="26000"
                    3->regionCode="27000"
                    4->regionCode="28000"
                    5->regionCode="29000"
                    6->regionCode="30000"
                    7->regionCode="31000"
                    8->regionCode="36110"
                    9->regionCode="41000"
                    10->regionCode="42000"
                    11->regionCode="43000"
                    12->regionCode="44000"
                    13->regionCode="45000"
                    14->regionCode="46000"
                    15->regionCode="47000"
                    16->regionCode="48000"
                    17->regionCode="50000"
                }
                if(regionCode.equals("00000")){
                    continueBtn.isEnabled = false
                }
                else {
                    continueBtn.isClickable = true
                    continueBtn.isEnabled = true
                }
            }
        }
    }
}