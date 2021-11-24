package org.techtown.retrofitlibrary_20211122.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.techtown.retrofitlibrary_20211122.fragments.MyProfileFragment
import org.techtown.retrofitlibrary_20211122.fragments.ProductLIstFragment
import org.techtown.retrofitlibrary_20211122.fragments.ReviewListFragment

class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {

            0 -> "리뷰목록"
            1 -> "상품목록"
            else -> "내 프로필"


        }
    }


    override fun getItem(position: Int): Fragment {
        return when (position) {

            0 -> ReviewListFragment()
            1 -> ProductLIstFragment()
            else -> MyProfileFragment()


        }
    }
}