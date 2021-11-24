package org.techtown.retrofitlibrary_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.retrofitlibrary_20211122.adapters.MainViewPagerAdapter
import org.techtown.retrofitlibrary_20211122.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var mvpa : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
//          뷰페이저 메인 엑티비티 자리에 연결
        mvpa = MainViewPagerAdapter( supportFragmentManager)
        binding.mainViewPager.adapter = mvpa

        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)// 탭레이아웃 메인 엑티비티 자리에 연결


    }
}