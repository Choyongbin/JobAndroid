package com.example.myapplication123
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity3 : AppCompatActivity() {

    var list_of_big = arrayOf("대분류","경영/사무/금융/보험", "연구/공학기술", "교육/법률/사회복지/경찰/소방/군인","보건/의료","예술/디자인/방송/스포츠" +
            "미용/여행/숙박/음식/경비/돌봄/청소","영업/판매/운전/운송","견설/채굴","설치/정비/생산-기계/금속/재료","설치/정비/생산-전기/전자/정보통신"+
            "설치/정비/생산-화학/환경/섬유/의복/식품가공","설치/정비/생산-인쇄/목재/공예/제조 단순","농림어업직")

    var list_of_bottom_01 = arrayOf("행정/경영/금융/보험 관리직", "교육·법률·복지·의료·예술·방송·정보통신 등 전문서비스 관리직", "미용·여행·숙박·음식 등 개인서비스 및 영업·판매·운송 관리직",
            "건설·채굴·제조·생산 관리직","행정·경영·회계·광고·상품기획 전문가","행정·경영·회계·광고·상품기획 전문가","정부·공공행정 사무","경영지원 사무","회계·경리 사무",
            "무역·운송·자재·구매·생산·품질 사무","안내·접수·고객상담 사무","통계·비서·사무보조·기타 사무","금융·보험 전문가","금융·보험 사무 및 영업" )

    var list_of_bottom_02 = arrayOf("인문·사회·자연·생명과학 연구 및 시험", "컴퓨터하드웨어·통신공학","컴퓨터시스템","소프트웨어","데이터·네트워크 및 시스템 운영","정보 보안 및 통신·방송 송출",
            "건설·채굴 연구 및 공학기술","기계·로봇·금속·재료 연구 및 공학기술","전기·전자 연구 및 공학기술","화학·에너지·환경 연구 및 공학기술","섬유·식품·소방·방재·산업안전 연구 및 공학기술",
            "제도사(3D프린팅포함) 및 기타 인쇄·목재 등 공학기술")

    var list_of_bottom_03 = arrayOf("대학교수, 학교 및 유치원 교사","문리·어학 강사","컴퓨터·기술·기능계 강사","예능·학습지·기타 강사","장학관·교육조교(RA포함) 및 교사·보육 보조","법률 전문가 및 법률 사무",
            "사회복지·상담·직업상담·시민단체활동","보육교사·생활지도원 및 종교직","경찰·소방·교도·군인")

    var list_of_bottom_04 = arrayOf("의사·한의사·치과의사","수의사·약사 및 한약사·간호사·영양사","의료기사·치료사·재활사","그 외 보건·의료 종사자")

    var list_of_bottom_05 = arrayOf("작가, 통·번역 및 출판물 전문가","기자 및 언론 전문가","학예사·사서·기록물관리사","창작·공연 (작가 및 연극 제외)","디자이너 (미디어콘텐츠 제외)","미디어콘텐츠·UX/UI 디자이너",
            "연극·영화·방송 (방송기술 포함)","공연·음반 기획 및 매니저","스포츠·레크리에이션")

    var list_of_bottom_06 = arrayOf("미용 및 반려동물 미용·관리","결혼·장례 등 예식 서비스","여행·객실승무·숙박·오락 서비스","주방장 및 조리사","식당 서비스(음식배달 포함)","경호·보안","경비원","돌봄 서비스",
            "청소·방역 및 가사 서비스","검침·주차관리 및 기타 단순 서비스")

    var list_of_bottom_07 = arrayOf("부동산중개, 기술·의약품 및 해외 영업","자동차·제품·광고 영업 및 상품 중개","텔레마케터(TM)","소규모 판매점장 및 상점 판매","통신서비스·온라인판매·상품대여·노점·이동판매 및 주유",
            "매장 계산 및 매표","판촉 및 기타 판매 종사자","항공기·선박·철도 조종 및 관제","자동차 운전(택시·버스·화물차·기타 자동차)","물품이동장비 조작","택배·납품영업·선박갑판·하역 및 기타 운송")

    var list_of_bottom_08 = arrayOf("건설구조 기능(철골·철근·석공·목공·조적 등)", "건축마감 기능(미장·단열·도배·새시·영선 등)", "배관","건설·채굴 기계 운전","기타 건설 기능(광원,채석,철로설치 등)","건설·채굴 단순 종사자")

    var list_of_bottom_09 = arrayOf("기계장비 설치·정비", "운송장비 정비", "금형 및 공작기계 조작", "냉난방 설비·자동 조립라인·산업용 로봇 조작", "기계 및 운송장비 조립", "금속관련 기계·설비 조작", "판금·제관·단조·주조",
            "용접", "도장·도금", "비금속제품 생산기계 조작")

    var list_of_bottom_10 = arrayOf("전기공(전기공사)","전기·전자 기기 설치·수리(사무용,가전제품,기타)","발전·배전 장비, 전기·전자 설비 조작(전기관리)", "전기·전자 부품·제품 생산기계 조작", "전기·전자 부품·제품 조립",
            "정보통신기기 설치·수리(컴퓨터,핸드폰)", "방송·통신장비 및 케이블 설치·수리")

    var list_of_bottom_11 = arrayOf("석유·화학물 가공장치 조작", "고무·플라스틱 및 화학제품 생산기계 조작", "환경 장치 조작(상하수도·재활용처리)", "섬유 제조 및 가공 기계 조작", "패턴·재단·재봉" , "의복 제조 및 수선",
            "제화, 기타 섬유·의복 기계 조작", "제과·제빵 및 떡 제조", "식품가공 기능원(도축·정육,김치·밑반찬제조 등)" , "식품가공 기계 조작")

    var list_of_bottom_12 = arrayOf("인쇄기계·사진현상기 조작","목재·펄프·종이 생산","가구·목제품 제조·수리","공예 및 귀금속 세공","악기·간판 및 기타 제조(드론조작 포함)","제조 단순 종사자")

    var list_of_bottom_13 = arrayOf("작물재배","낙농·사육","임업 종사자","어업 종사자","농림어업 단순 종사자")

    var jobCode:String = "00"
    private var userID:String = ""
    private var userPW:String= ""
    private var userNAME:String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

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
        //var backBtn = findViewById<Button>(R.id.backBtn)

        Log.d("userID", userID)
        Log.d("userName", userNAME)
        Log.d("userPW", userPW)

        continueBtn.setOnClickListener{

            val i = Intent(this, selectRegion::class.java)
            i.putExtra("jobCode", jobCode)
            i.putExtra("userName",userNAME)
            i.putExtra("userID", userID)
            i.putExtra("userPass", userPW)
            startActivity(i)
        }


        /*
        backBtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
         */
    }

    fun initFilter(){
        var topSpinner = findViewById<Spinner>(R.id.spinnerTopClass)
        var bottomSpinner = findViewById<Spinner>(R.id.spinnerBottomClass)
        var continueBtn = findViewById<AppCompatButton>(R.id.continueBtn)
//        topSpinner.prompt="대분류"
//        bottomSpinner.prompt="중분류"

        topSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_big)
        topSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> jobCode ="00"
                    1 -> jobCode = "01"
                    2 -> jobCode = "02"
                    3 -> jobCode = "03"
                    4 -> jobCode = "04"
                    5 -> jobCode = "05"
                    6 -> jobCode = "06"
                    7 -> jobCode = "07"
                    8 -> jobCode = "08"
                    9 -> jobCode = "09"
                    10 -> jobCode = "10"
                    11 -> jobCode = "11"
                    12 -> jobCode = "12"
                    13 -> jobCode = "13"
                }
                if(jobCode.equals("00")){
                    bottomSpinner.visibility = View.GONE
                    continueBtn.isEnabled = false
                }
                else {
                    bottomSpinner.visibility = View.VISIBLE
                    continueBtn.isClickable = true
                        continueBtn.isEnabled = true
                    changeBottomSpinner()
                }
            }
        }
    }

    fun changeBottomSpinner(){
        var bottomSpinner = findViewById<Spinner>(R.id.spinnerBottomClass)
        var continueBtn = findViewById<Button>(R.id.continueBtn)

        //bottomSpinner.visibility = View.VISIBLE

        if(jobCode == "01"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_01)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                        9 -> jobCode += "A"
                        10 -> jobCode += "B"
                        11 -> jobCode += "C"
                        12 -> jobCode += "D"
                    }
                }
            }
        }
        else if(jobCode == "02"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_02)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                        9 -> jobCode += "A"
                        10 -> jobCode += "B"
                        11 -> jobCode += "C"
                    }
                }
            }
        }
        else if(jobCode == "03"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_03)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                    }
                }
            }
        }
        else if(jobCode == "04"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_04)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                    }
                }
            }
        }
        else if(jobCode == "05"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_05)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                    }
                }
            }
        }
        else if(jobCode == "06"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_06)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                        9 -> jobCode += "A"
                    }
                }
            }
        }
        else if(jobCode == "07"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_07)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                        9 -> jobCode += "A"
                        10 -> jobCode += "B"
                    }
                }
            }
        }
        else if(jobCode == "08"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_08)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                    }
                }
            }
        }
        else if(jobCode == "09"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_09)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                        9 -> jobCode += "A"
                    }
                }
            }
        }
        else if(jobCode == "10"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_10)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                    }
                }
            }
        }
        else if(jobCode == "11"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_11)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                        6 -> jobCode += "7"
                        7 -> jobCode += "8"
                        8 -> jobCode += "9"
                        9 -> jobCode += "A"
                    }
                }
            }
        }
        else if(jobCode == "12"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_12)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                        5 -> jobCode += "6"
                    }
                }
            }
        }
        else if(jobCode == "13"){
            bottomSpinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_bottom_13)
            bottomSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> jobCode += "1"
                        1 -> jobCode += "2"
                        2 -> jobCode += "3"
                        3 -> jobCode += "4"
                        4 -> jobCode += "5"
                    }
                }
            }
        }

    }
}