package com.junjange.touring.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserState
import com.amazonaws.mobile.client.UserStateDetails
import com.junjange.touring.R
import com.junjange.touring.databinding.FragmentUserBinding
import com.junjange.touring.ui.tip.TipFragment
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    fun newInstance() : UserFragment {
        return UserFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

}