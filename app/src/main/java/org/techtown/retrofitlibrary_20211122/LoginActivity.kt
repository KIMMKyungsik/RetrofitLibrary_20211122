package org.techtown.retrofitlibrary_20211122

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import org.techtown.retrofitlibrary_20211122.databinding.ActivityLoginBinding
import org.techtown.retrofitlibrary_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setupEvents()
        setValues()


    }

    override fun setupEvents() {

        binding.btnSignUp.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)


        }


        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            apiService.postRequestLogin(inputEmail, inputPw)
                .enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {

                        if (response.isSuccessful) {

                            val basicResponse = response.body()!!

//                        Toast.makeText(mContext,basicResponse.message, Toast.LENGTH_SHORT).show()
//                      추가 파싱 -> 로그인한 사람 닉네임 활용 "~님 환영합니다!" 토스트

                            val userNickname = basicResponse.data.user.nickname

                            Toast.makeText(mContext, "${userNickname}님 환영합니다!", Toast.LENGTH_SHORT)
                                .show()

                        } else {

                            val errorJason = JSONObject(response.errorBody()!!.string())
                            Log.d("에러경우", errorJason.toString())

                            val message = errorJason.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        }

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }


                })


        }
    }

    override fun setValues() {

    }


}










