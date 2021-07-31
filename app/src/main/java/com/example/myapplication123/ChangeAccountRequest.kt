package com.example.myapplication123

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class ChangeAccountRequest: StringRequest {
    companion object {
        //서버 URL 연결 (php 파일 연동)
        private const val URL = "http://freddy456.dothome.co.kr/UserNameUpdate.php"
    }

    private var map: MutableMap<String, String>? = null

    constructor(
        userName: String,
        userID: String,
        responseListener: Any
    ) : super(
        Method.POST, URL, responseListener as Response.Listener<String>?, null
    ) {
        map = HashMap()
        (map as HashMap<String, String>)["userName"] = userName
        (map as HashMap<String, String>)["userID"] = userID
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