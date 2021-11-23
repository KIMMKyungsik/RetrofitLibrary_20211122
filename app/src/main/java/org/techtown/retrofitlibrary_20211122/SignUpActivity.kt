package org.techtown.retrofitlibrary_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import org.techtown.retrofitlibrary_20211122.databinding.ActivitySignUpBinding
import org.techtown.retrofitlibrary_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    var isDuplOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        setValues()
        setupEvents()


    }


    override fun setupEvents() {


        binding.edtEmail.addTextChangedListener {

//            재검사 요청
            binding.txtEmailCheckResult.text = "이메일 중복검사를 해주세요."
            isDuplOk= false

        }

        binding.btnEmailCheck.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            apiService.getRequestDuplicatedCheck( "EMAIL",email).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    
                    if (response.isSuccessful){
//                        code: 200 -> 사용해도 좋은 이메일.
                        binding.txtEmailCheckResult.text = "사용해도 좋은 이메일 입니다."
                        isDuplOk=true
                        
                    }
                    else{
//                        사용하면 안되는 이메일
                        binding.txtEmailCheckResult.text = "다른 이메일로 다시 검사해주세요."
                        isDuplOk= false
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })


        }

        binding.btnSignUp.setOnClickListener {

            if (!isDuplOk){

                Toast.makeText(mContext, "이메일 중복검사를 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val nickname = binding.edtNickname.text.toString()

        apiService.putRequestSignUp(email, password, nickname).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {



                if (response.isSuccessful){
                    val br = response.body()!!

                    Log.d("가입한사람 토큰",br.data.token)

                    val signUpUserNickname = br.data.user.nickname
                    Toast.makeText(mContext, "${signUpUserNickname}님 가입을 축하합니다!", Toast.LENGTH_SHORT).show()
                    finish()


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