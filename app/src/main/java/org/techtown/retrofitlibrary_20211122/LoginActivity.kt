package org.techtown.retrofitlibrary_20211122

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.kakao.sdk.user.UserApiClient
import org.json.JSONObject
import org.techtown.retrofitlibrary_20211122.databinding.ActivityLoginBinding
import org.techtown.retrofitlibrary_20211122.datas.BasicResponse
import org.techtown.retrofitlibrary_20211122.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import kotlin.math.log

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setupEvents()
        setValues()


    }

    override fun setupEvents() {

        binding.btnkakaoLogin.setOnClickListener {

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {

//               카톡 앱이 깔려있는 상황
                UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->

                    if (error != null) {
                        Log.e("카톡 로그인", "로그인 실패")

                    } else if (token != null) {

                        Log.e("카톡 로그인", "로그인 성공")
                        Log.e("카톡 로그인", token.accessToken)

                        getMyInfoFromKakao()


                    }
                }
            } else {

//                앱은 안깔려있는 상황

                UserApiClient.instance.loginWithKakaoAccount(mContext) { token, error ->


                    if (error != null) {
                        Log.e("카톡 로그인", "로그인 실패")

                    } else if (token != null) {

                        Log.e("카톡 로그인", "로그인 성공")
                        Log.e("카톡 로그인", token.accessToken)

                        getMyInfoFromKakao()


                    }


                }
            }



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

                                Toast.makeText(
                                    mContext,
                                    "${userNickname}님 환영합니다!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

//                                토큰값 추출 -> 기기에 저장 ( SharedPreferences)

                                ContextUtil.setToken(mContext, basicResponse.data.token)


                                val myIntent = Intent (mContext,MainActivity::class.java)
                                startActivity(myIntent)

                                finish()

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


    }

    override fun setValues() {

    }

    fun getMyInfoFromKakao() {

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("카톡 로그인", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    "카톡 로그인", "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}"
                )
                apiService.postRequestSocialLogin(
                    "kakao",
                    user.id.toString(),
                    user.kakaoAccount?.profile?.nickname!!
                ).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {


                        if (response.isSuccessful){

                            val br = response.body()!!

                            Toast.makeText(mContext, "${br.data.user.nickname}", Toast.LENGTH_SHORT).show()


                            ContextUtil.setToken(mContext, br.data.token)
                            val myIntent = Intent (mContext,MainActivity::class.java)
                            startActivity(myIntent)

                            finish()

                        }

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }


                })


            }
        }


    }


}






