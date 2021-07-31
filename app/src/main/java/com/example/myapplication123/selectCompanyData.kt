package com.example.myapplication123

import java.io.Serializable

data class selectCompanyData(val comapny:String, val title:String, val salTpNm:String, val sal:String, val region:String,
val holidayTpNm:String, val minEdubg:String, val career:String, val regDt:String, val closeDt:String, val wantedMobileInfoUrl:String, val basicAddr: String) :
    Serializable {}