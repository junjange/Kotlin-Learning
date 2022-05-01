package com.twentyfour.bulletin_board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.twentyfour.bulletin_board.databinding.ActivityWriteBoardBinding

class WriteBoardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityWriteBoardBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this,MainViewModel.Factory(application))[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 데이터 바인딩
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

    fun onClickAdd(view: View) {
        viewModel.insertRetrofit(binding.titleEdit.text.toString(), binding.contentEdit.text.toString())

        // 입력을 하고 MainActivity 이동
        val intent = Intent(this@WriteBoardActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}