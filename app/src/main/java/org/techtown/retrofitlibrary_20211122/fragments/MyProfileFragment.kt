package org.techtown.retrofitlibrary_20211122.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.techtown.retrofitlibrary_20211122.R
import org.techtown.retrofitlibrary_20211122.databinding.FragmentMyProfileBinding

class MyProfileFragment :Fragment(){
    lateinit var binding: FragmentMyProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = DataBindingUtil.inflate(inflater,
             R.layout.fragment_my_profile, container, false)
      return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }


}