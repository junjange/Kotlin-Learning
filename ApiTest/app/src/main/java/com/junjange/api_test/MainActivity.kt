package com.junjange.api_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory


var text = ""

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        textView.text = ""
        // 키 값
        val key = "키값"
        // 현재 페이지번호
        val pageNo = "&pageNo=1"
        // 한 페이지 결과 수
        val numOfRows ="&numOfRows=5"
        // AND(안드로이드)
        val MobileOS = "&MobileOS=AND"
        // 서비스명 = 어플명
        val MobileApp = "&MobileApp=AppTest"
        // API 정보를 가지고 있는 주소
        val url = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList?serviceKey="+key+pageNo+numOfRows+MobileOS+MobileApp

        // 버튼을 누르면 쓰레드 동작
        button.setOnClickListener {
            // 쓰레드 생성
            val thread = Thread(NetworkThread(url))
            thread.start() // 쓰레드 시작
            thread.join() // 멀티 작업 안되게 하려면 start 후 join 입력

            // 쓰레드에서 가져온 api 정보 텍스트에 뿌려주기
            textView.text = text
        }
    }

}

class NetworkThread(
    var url: String): Runnable {

    override fun run() {

        try {

            val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)


            xml.documentElement.normalize()

            //찾고자 하는 데이터가 어느 노드 아래에 있는지 확인
            val list:NodeList = xml.getElementsByTagName("item")

            //list.length-1 만큼 얻고자 하는 태그의 정보를 가져온다
            for(i in 0..list.length-1){

                val n:Node = list.item(i)

                if(n.getNodeType() == Node.ELEMENT_NODE){

                    val elem = n as Element

                    val map = mutableMapOf<String,String>()


                    // 이부분은 어디에 쓰이는지 잘 모르겠다.
                    for(j in 0..elem.attributes.length - 1) {

                        map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)

                    }


                    println("=========${i+1}=========")
                    text += "${i + 1}번 캠핑장 \n"

                    println("1. 주소 : ${elem.getElementsByTagName("addr1").item(0).textContent}")
                    text += "1. 주소 : ${elem.getElementsByTagName("addr1").item(0).textContent} \n"

                    println("2. 캠핑장 이름 : ${elem.getElementsByTagName("facltNm").item(0).textContent}")
                    text += "2. 캠핑장 이름 : ${elem.getElementsByTagName("facltNm").item(0).textContent} \n"

                    println("3. 위도 : ${elem.getElementsByTagName("mapX").item(0).textContent}")
                    text += "3. 위도 : ${elem.getElementsByTagName("mapX").item(0).textContent} \n"

                    println("4. 경도 : ${elem.getElementsByTagName("mapY").item(0).textContent}")
                    text += "4. 경도 : ${elem.getElementsByTagName("mapY").item(0).textContent} \n"

                    println("5. 업종 : ${elem.getElementsByTagName("induty").item(0).textContent}")
                    text += "5. 업종 : ${elem.getElementsByTagName("induty").item(0).textContent} \n"

                }
            }
        } catch (e: Exception) {
            Log.d("TTT", "오픈API"+e.toString())
        }
    }
}