package com.junjange.touring.ui.tip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.junjange.touring.R
import com.junjange.touring.databinding.FragmentHomeBinding
import com.junjange.touring.databinding.FragmentTipBinding
import com.junjange.touring.ui.shop.ShopFragment

class TipFragment : Fragment() {

    fun newInstance() : TipFragment {
        return TipFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tip, container, false)
    }

}