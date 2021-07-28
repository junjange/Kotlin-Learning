package com.junjange.touring

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.junjange.touring.ui.home.HomeFragment
import com.junjange.touring.ui.search.SearchFragment
import com.junjange.touring.ui.shop.ShopFragment
import com.junjange.touring.ui.tip.TipFragment
import com.junjange.touring.ui.user.AuthActivity
import com.junjange.touring.ui.user.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var shopFragment: ShopFragment
    private lateinit var tipFragment: TipFragment
    private lateinit var userFragment: UserFragment

    private val TAG = AuthActivity::class.java.simpleName

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 레이아웃과 연결
        setContentView(R.layout.activity_main)

        // 바텀네비게이션 변수 실행
        nav_view.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

        // 처음 뜨는 fragment 설정
        homeFragment = HomeFragment().newInstance()
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, homeFragment).commit()

        // 로그인이 되어있는지 확인
        AWSMobileClient.getInstance()
            .initialize(applicationContext, object : Callback<UserStateDetails> {
                override fun onResult(userStateDetails: UserStateDetails) {
                    Log.i(TAG, userStateDetails.userState.toString())


                }

                override fun onError(e: Exception) {
                    Log.e(TAG, e.toString())
                }

            })


    }

    // 바텀 네비게이션 아이템 클릭 리스터 설정
    private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{
            val ap = true
            when (it.itemId) {
                R.id.navigation_home -> {
                    homeFragment = HomeFragment().newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, homeFragment).commit()
                    Log.d("ttt","홈")
                }
                R.id.navigation_search -> {
                    searchFragment = SearchFragment().newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, searchFragment).commit()
                    Log.d("ttt","검색")

                }
                R.id.navigation_shop -> {
                    shopFragment = ShopFragment().newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, shopFragment).commit()
                    Log.d("ttt","샵")

                }
                R.id.navigation_tip -> {
                    tipFragment = TipFragment().newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, tipFragment).commit()
                    Log.d("ttt","팁")

                }
                R.id.navigation_user -> {
                    if(ap == true) {
                        userFragment = UserFragment().newInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, userFragment).commit()
                        Log.d("ttt","유저")

                    }
                }
            }
            true
    }

}


