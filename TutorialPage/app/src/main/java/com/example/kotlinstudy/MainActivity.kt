package com.example.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstudy.ViewModel.ActionType
import com.example.kotlinstudy.ViewModel.MyNumberViewModel
import com.example.kotlinstudy.ViewModel.MyNumberViewModel.Companion.TAG
import com.example.kotlinstudy.adapter.ProfileAdapter
import com.example.kotlinstudy.databinding.ActivityMainBinding
import com.example.kotlinstudy.model.ProfileData
import android.content.Intent
import android.app.Activity

import android.content.SharedPreferences







class MainActivity : AppCompatActivity() {

    lateinit var myNumberViewModel: MyNumberViewModel
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this@MainActivity
        setRcv()

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        myNumberViewModel.currentValue.observe(this, {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이타 값 변경: $it")
            binding.numberTextview.text = it.toString()


        })

        // 최초 실행 여부를 판단 ->>>
        val pref = getSharedPreferences("checkFirst", MODE_PRIVATE)
        val checkFirst = pref.getBoolean("checkFirst", false)

        // false일 경우 최초 실행
        if (!checkFirst) {
            // 앱 최초 실행시 하고 싶은 작업
            val editor = pref.edit()
            editor.putBoolean("checkFirst", true)
            editor.apply()
            finish()
            val intent = Intent(this@MainActivity, TutorialActivity::class.java)
            startActivity(intent)
        }

    }

    fun calBtn(view : View){
        val userInput = binding.numberInputEdittext.text.toString().toInt()

        when(view){
            binding.plusBtn -> myNumberViewModel.updateValue(ActionType.PLUS, userInput)
            binding.minusBtn -> myNumberViewModel.updateValue(ActionType.MINUS, userInput)
        }



    }
    fun btnClick(view : View){
        Toast.makeText(this, "Button Click", Toast.LENGTH_SHORT).show()

    }

    fun setRcv(){
        val profileAdapter = ProfileAdapter(this)
        binding.mainRcv.layoutManager = LinearLayoutManager(this)
        binding.mainRcv.adapter = profileAdapter
        profileAdapter.data = listOf(
            ProfileData(name = "Kang", age = 26),
            ProfileData(name = "Kim", age = 25)
        )
        profileAdapter.notifyDataSetChanged()
    }

    fun tutorialBtnClick(view: View){
        val intent = Intent(this@MainActivity, TutorialActivity::class.java)
        startActivity(intent)
        finish()
    }
}