package org.techtown.retrofitlibrary_20211122.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

//    Retrofit 타입 객체 하나만 생성 -> 모두가 공유(싱글턴 패턴)

    companion object{

        private  var BASE_URL = "https://api.gudoc.in"

       private var retrofit : Retrofit? = null // 앱이 처음 켜질때는 없다. -> 한번만 만들고 함수를 통해서 공유.

        fun getRetrofit() : Retrofit{

            if (retrofit == null ) {
//          통신 담당 객체를 만들지 않았다면 -> 없을때만 새로 한번만들자.

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)// 어느 서버로 접속?
                    .addConverterFactory(GsonConverterFactory.create())//파싱을 자동으로 이용해주는 도구
                    .build()

            }

            return retrofit!!

        }
    }

}