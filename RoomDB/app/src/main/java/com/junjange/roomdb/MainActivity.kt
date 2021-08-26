package com.junjange.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // db 연결
        val db = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        // db에 저장된 데이터 불러오기
        db.dao().getAll().observe(this, Observer { todos ->
            result_text.text = todos.toString()

        })

        // 글을 쓰고 버튼을 누르면 db에 저장
        button.setOnClickListener{
            db.dao().insert(Entity(edit.text.toString()))
        }

    }

}