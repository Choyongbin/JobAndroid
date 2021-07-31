package com.example.myapplication123

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class LoginRequest : StringRequest {
    private var map: MutableMap<String, String>? = null

    constructor(
        userID: String,
        userPassword: String,
        responseListener: Response.Listener<String>
        //listener: Response.Listener<String?>?
    ) : super(
        Method.POST, URL, responseListener, null
    ) {
        map = HashMap()
        (map as HashMap<String, String>)["userID"] = userID
        (map as HashMap<String, String>)["userPassword"] = userPassword
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

    companion object {
        //서버 URL 연결 (php 파일 연동)
        private const val URL = "http://freddy456.dothome.co.kr/Login.php"
    }
}