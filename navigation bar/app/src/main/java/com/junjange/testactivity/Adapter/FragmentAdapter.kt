package com.junjange.testactivity.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.junjange.testactivity.Fragment.HomeFragment
import com.junjange.testactivity.Fragment.MapFragment
import com.junjange.testactivity.Fragment.MyPageFragment
import com.junjange.testactivity.Fragment.OtherFragment

class FragmentAdapter(fm : FragmentManager, private val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return HomeFragment()
            1 -> return MyPageFragment()
            2 -> return OtherFragment()
            3 -> return MapFragment()
            else -> return Fragment()
        }
    }

    override fun getCount(): Int = fragmentCount

}