package com.example.myapplication123

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.myapplication123.LoginRequest
import com.example.myapplication123.RegisterActivity
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private var et_password: EditText? = null
    private var et_ID: EditText? = null
    private var btn_login: Button? = null
    private var btn_register: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_ID = findViewById(R.id.et_ID)
        et_password = findViewById(R.id.et_password)
        btn_login = findViewById(R.id.btn_login)
        btn_register = findViewById(R.id.btn_register)
        btn_register?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        })
        val layout3:ConstraintLayout = findViewById(R.id.layout3)
        layout3.setOnClickListener {
            hideKeyboard()
        }
        btn_login?.setOnClickListener(View.OnClickListener {
            val userID = et_ID?.text.toString()
            val userPass = et_password?.text.toString()
            val responseListener: Response.Listener<String> =
                Response.Listener { response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success")
                        if (success) {
                            val userID = jsonObject.getString("userID")
                            val userPass = jsonObject.getString("userPassword")
                            val userName = jsonObject.getString("userName")
                            val intent = Intent(
                                this@LoginActivity,
                                MainActivity::class.java
                            ) //여기서 MainActivity로 넘기지말고 다음 화면으로 넘기면 됨
                            intent.putExtra("userID", userID)
                            intent.putExtra("userPass", userPass)
                            intent.putExtra("userName", userName)
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
                            return@Listener
                        }
                    } catch (e: JSONException) {
                       e.printStackTrace()
                    }
                }
            val loginRequest = LoginRequest(userID, userPass, responseListener)
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(loginRequest)
        })
    }

    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_password?.windowToken, 0)
    }

}