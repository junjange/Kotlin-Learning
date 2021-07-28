package com.junjange.touring.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.junjange.touring.R
import com.junjange.touring.databinding.FragmentShopBinding
import com.junjange.touring.ui.search.SearchFragment

class ShopFragment : Fragment() {

    fun newInstance() : ShopFragment {
        return ShopFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

}