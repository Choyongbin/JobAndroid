package com.example.myapplication123

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class ChangePW : AppCompatActivity() {
    var flag = false
    var flag2 = false

    private var userID:String = ""
    private var userPW:String= ""
    private var userNAME:String= ""
    lateinit var newCheckPW:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pw)
        init()
    }

    fun init(){
        setSupportActionBar(findViewById(R.id.toolbar))
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)
        if(intent.hasExtra("userPw")){
            userPW = intent.getStringExtra("userPw").toString()
        }
        if(intent.hasExtra("userID")){
            userID = intent.getStringExtra("userID").toString()
        }
        if(intent.hasExtra("userName")){
            userNAME = intent.getStringExtra("userName").toString()
        }
        if(intent.hasExtra("userPass")){
            userPW = intent.getStringExtra("userPass").toString()
        }

        val currentPW = findViewById<EditText>(R.id.currentPWEditText)
        val newPW = findViewById<EditText>(R.id.newPWEditText)
        newCheckPW = findViewById<EditText>(R.id.newPWCheckEditText)
        val changePWBtn = findViewById<Button>(R.id.changePWContinueBtn)
        val layout1 = findViewById<LinearLayout>(R.id.layout1)
        currentPW.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkCurrentPW(currentPW)
            }

        })
        newCheckPW.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkNewPW(newPW, newCheckPW)

            }

            override fun afterTextChanged(s: Editable?) {
                if(flag && flag2){
                    changePWBtn.isClickable = true
                    changePWBtn.isEnabled = true
                }
            }

        })

        changePWBtn.setOnClickListener {
            userPW = newCheckPW.text.toString()
            if(flag && flag2){
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("userID", userID)
                i.putExtra("userPass", userPW)
                i.putExtra("userName", userNAME)

                val resposeListener: Any = object : Response.Listener<String?> {
                    override fun onResponse(response: String?) {
                        try {
                            val jsonObject = JSONObject(response)

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
                val loginRequest = ChangePWRequest(userPW, userID, resposeListener)
                val queue = Volley.newRequestQueue(this@ChangePW)
                queue.add(loginRequest)
                startActivity(i)
            }
        }
        layout1.setOnClickListener{
            hideKeyboard()
        }

    }
    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(newCheckPW.windowToken, 0)
    }

    fun checkCurrentPW(currentPw: EditText){
        if(currentPw.text.toString().equals(userPW)){
            val wrongText:TextView = findViewById(R.id.textView15)
            wrongText.visibility = View.GONE
            currentPw.setBackgroundResource(R.drawable.inputbox1)
            flag2 = true
        }
        else{
            val wrongText:TextView = findViewById(R.id.textView15)
            wrongText.visibility = View.VISIBLE
            currentPw.setBackgroundResource(R.drawable.inputbox2)
            flag2 = false
        }

    }

    fun checkNewPW(EditText1 : EditText, EditText2: EditText){
        if(EditText1.text.toString().equals(EditText2.text.toString())){
            val wrongText :TextView = findViewById(R.id.textView14)
            wrongText.visibility = View.GONE
            EditText2.setBackgroundResource(R.drawable.inputbox1)
            flag = true
        }
        else{
            val wrongText :TextView = findViewById(R.id.textView14)
            wrongText.visibility = View.VISIBLE
            EditText2.setBackgroundResource(R.drawable.inputbox2)
            flag = false
        }
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
}