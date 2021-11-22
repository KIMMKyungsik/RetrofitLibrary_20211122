package org.techtown.retrofitlibrary_20211122

import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.retrofitlibrary_20211122.api.ServerAPI
import org.techtown.retrofitlibrary_20211122.api.ServerAPIService

abstract class BaseActivity :AppCompatActivity() {

    lateinit var mContext:Context

    lateinit var apiService : ServerAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiService = retrofit.create(ServerAPIService::class.java)



    }


    abstract  fun setupEvents()
    abstract  fun setValues()

}