package com.junjange.touring.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.junjange.touring.R
import com.junjange.touring.databinding.FragmentSerchBinding
import com.junjange.touring.ui.home.HomeFragment

class SearchFragment : Fragment() {

    fun newInstance() : SearchFragment {
        return SearchFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_serch, container, false)
    }

}