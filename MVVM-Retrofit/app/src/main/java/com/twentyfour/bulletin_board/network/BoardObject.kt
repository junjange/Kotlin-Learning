package com.twentyfour.bulletin_board.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
OkHttp는 HTTP 기반의 request/response를 쉽게 할 수 있도록 해주는 라이브러리이다.
Square에서 제공하는 오픈소스 라이브러리로 HTTP 통신을 쉽게 할 수 있게 해준다.
안드로이드에서 Okhttp 없이 Http 통신을 하게 되면 예외처리, Buffer입출력, HttpURLConnection연결 등 할게 엄청 많아진다고 한다.

object로 싱글톤으로 객체를 생성한다.
여기서 getRetrofit와 getRetrofitService를 by lazy 로 늦은 초기화 해줌으로써,
api 변수가 사용될 때 초기화되고, 그 안에서 retrofit 변수를 사용하기 때문에 초기화 된다.
 **/
object BoardObject {
    // 서버 주소
    private const val BASE_URL = "http://3.37.103.23/bulletin_board/"
    var token: String = ""

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
    }).addInterceptor {
            // Request
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()

            // Response
            val response = it.proceed(request)
            response
        }.build()


    private val getRetrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getRetrofitService : BoardInterface by lazy{
        getRetrofit.create(BoardInterface::class.java)
    }
}