package org.techtown.retrofitlibrary_20211122

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication :Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"51fdc971acfcb03e2bb7eedd93660978")



    }


}