package org.techtown.retrofitlibrary_20211122.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.techtown.retrofitlibrary_20211122.R
import org.techtown.retrofitlibrary_20211122.databinding.FragmentProductListBinding

class ProductLIstFragment :Fragment(){
    lateinit var binding: FragmentProductListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = DataBindingUtil.inflate(inflater,
             R.layout.fragment_product_list, container, false)
      return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }


}