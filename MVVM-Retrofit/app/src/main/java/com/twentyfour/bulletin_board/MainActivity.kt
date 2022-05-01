package com.twentyfour.bulletin_board

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.twentyfour.bulletin_board.adapter.BoardRecyclerAdapter
import com.twentyfour.bulletin_board.data.ModelBoardComponent
import com.twentyfour.bulletin_board.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this,MainViewModel.Factory(application))[MainViewModel::class.java] }
    private var isFabOpen = false // Fab 버튼 default는 닫혀있음
    private lateinit var retrofitAdapter: BoardRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 데이터 바인딩
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setView() // 리사이클러 뷰 연결
        setObserver() // 뷰모델을 관찰합니다.

        binding.fabMain.setOnClickListener {
            // 플로팅 버튼 클릭시 애니메이션 동작 기능
            toggleFab()

            // 플로팅 버튼 클릭 이벤트 - 글 쓰기
            binding.fabEdit.setOnClickListener {
                val intent = Intent(this, writeBoardActivity::class.java)
                startActivity(intent)

//              Toast.makeText(this, "글 쓰기 버튼 클릭", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setView(){
        retrofitAdapter =  BoardRecyclerAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvList.adapter = retrofitAdapter
    }

    private fun setObserver() {
        viewModel.retrofitTodoList.observe(this, {

            viewModel.retrofitTodoList.value?.let { it1 -> retrofitAdapter.setData(it1) }
        })

    }

    private fun toggleFab() {

        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabEdit, "translationY", 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding.fabEdit, "translationY", -250f).apply { start() }
        }

        isFabOpen = !isFabOpen

    }
}