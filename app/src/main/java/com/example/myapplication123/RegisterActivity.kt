package com.example.myapplication123

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.myapplication123.LoginActivity
import com.example.myapplication123.R
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    var flag = false
    var flag2 = false
    lateinit var et_passcheck:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(findViewById(R.id.toolbar))
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)
        val et_name = findViewById<EditText>(R.id.et_name)
        val et_id = findViewById<EditText>(R.id.et_id)
        val et_pass = findViewById<EditText>(R.id.et_pass)
        et_passcheck = findViewById<EditText>(R.id.et_passcheck)
        val bt_register = findViewById<Button>(R.id.bt_register)
        val layout4 = findViewById<ConstraintLayout>(R.id.layout4)
        layout4.setOnClickListener {
            hideKeyboard()
        }
        et_passcheck.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkNewPW(et_pass, et_passcheck)
            }

            override fun afterTextChanged(s: Editable?) {
                checkAllin(et_id,et_name,et_pass,et_passcheck)
                if(flag && flag2){
                    bt_register.isClickable = true
                    bt_register.isEnabled = true
                }
            }

        })

        bt_register.setOnClickListener(View.OnClickListener { // EditText 에ㅐ 현재 입력되어있는 값을 가져온다.
            val userID = et_id?.getText().toString()
            val userPass = et_pass?.getText().toString()
            val userName = et_name?.getText().toString()
            val resposeListener: Any = object : Response.Listener<String?> {
                override fun onResponse(response: String?) {
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success")
                        if (success) {
                            if (et_pass == et_passcheck) {
                                Toast.makeText(
                                    applicationContext,
                                    "Password Inconsistency",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return
                            }
                            Toast.makeText(applicationContext, "Confirm", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
                            return
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val registerRequest = RegisterRequest(userID, userPass, userName, resposeListener)
            val queue: RequestQueue = Volley.newRequestQueue(this@RegisterActivity)
            queue.add(registerRequest)
        })
    }
    fun checkAllin(EditText1 : EditText, EditText2: EditText,EditText3 : EditText, EditText4: EditText){
        if(EditText3.text.isNotEmpty()&&EditText4.text.isNotEmpty()){
            flag2 = true
        }
    }
    fun checkNewPW(EditText1 : EditText, EditText2: EditText){
        if(EditText1.text.toString().equals(EditText2.text.toString())){
            val wrongText : TextView = findViewById(R.id.textView14)
            wrongText.visibility = View.GONE
            EditText2.setBackgroundResource(R.drawable.inputbox1)
            flag = true
        }
        else{
            val wrongText : TextView = findViewById(R.id.textView14)
            wrongText.visibility = View.VISIBLE
            EditText2.setBackgroundResource(R.drawable.inputbox2)
            flag = false
        }
    }


    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_passcheck.windowToken, 0)
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