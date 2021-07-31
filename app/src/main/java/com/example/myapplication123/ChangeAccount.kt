package com.example.myapplication123

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class ChangeAccount : AppCompatActivity() {
    private var userID:String = ""
    private var userPW:String= ""
    private var userNAME:String= ""
    lateinit var currentAccount:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_account)

        if(intent.hasExtra("userID")){
            userID = intent.getStringExtra("userID").toString()
            Log.d("userID", userID)
        }
        if(intent.hasExtra("userName")){
            userNAME = intent.getStringExtra("userName").toString()
            Log.d("userName", userNAME)
        }
        if(intent.hasExtra("userPass")){
            userPW = intent.getStringExtra("userPass").toString()
            Log.d("userPW", userPW)
        }
        init()
    }

    fun init(){

        setSupportActionBar(findViewById(R.id.toolbar))
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        currentAccount = findViewById<EditText>(R.id.changeNameEditText)
        val currentChangePWContinueBtn: Button = findViewById(R.id.changeNameContinueBtn)
        val layout2:LinearLayout = findViewById(R.id.layout2)
        currentAccount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                currentChangePWContinueBtn.isEnabled = currentAccount.text.toString().isNotEmpty()
                currentChangePWContinueBtn.isClickable = currentAccount.text.toString().isNotEmpty()
                userNAME = currentAccount.text.toString()
                //사용자 이름 바꾸기
            }

        })

        layout2.setOnClickListener {
            hideKeyboard()
        }

        currentChangePWContinueBtn.setOnClickListener {
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
            val loginRequest = ChangeAccountRequest(currentAccount.text.toString(), userID, resposeListener)
            val queue = Volley.newRequestQueue(this@ChangeAccount)
            queue.add(loginRequest)
            startActivity(i)
        }

    }

    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentAccount.windowToken, 0)
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