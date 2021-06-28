package com.junjange.testactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import com.junjange.testactivity.Adapter.FragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("UNUSED_CHANGED_VALUE")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureBottomNavigation()

    }

    private fun configureBottomNavigation(){
        viewpager.adapter = FragmentAdapter(supportFragmentManager, 4)

        bottom_menu.setupWithViewPager(viewpager)

        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.activity_transform, null, false)

        bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.navi_home) as RelativeLayout
        bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.navi_my_page) as RelativeLayout
        bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.navi_other) as RelativeLayout
        bottom_menu.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.navi_map) as RelativeLayout

    }
}