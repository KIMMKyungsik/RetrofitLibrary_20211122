package org.techtown.retrofitlibrary_20211122.datas

import com.google.gson.annotations.SerializedName

class UserData(
    var id: Int,
    var email:String,


//    서버의 이름표와 변수의 이름이 다를때

    @SerializedName("nick_name")
    var nickname:String,
    @SerializedName("profile_img")
    var profileImageURL:String


) {
}