package com.example.myapplication123

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class RegisterRequest : StringRequest {

    companion object {
        //서버 URL 연결 (php 파일 연동)
        private const val URL = "http://freddy456.dothome.co.kr/Register.php"
    }

    private var map: MutableMap<String, String>? = null
    constructor(
        userID: String,
        userPassword: String,
        userName: String,
        listener: Any
    ) : super(
        Method.POST, URL, listener as Response.Listener<String>?, null
    ) {
        map = HashMap()
        (map as HashMap<String, String>)["userID"] = userID
        (map as HashMap<String, String>)["userPassword"] = userPassword
        (map as HashMap<String, String>)["userName"] = userName
    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map!!
    }

    constructor(
        url: String?,
        listener: Response.Listener<String?>?,
        errorListener: Response.ErrorListener?
    ) : super(url, listener, errorListener) {
    }


}