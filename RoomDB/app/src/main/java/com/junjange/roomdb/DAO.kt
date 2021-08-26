package com.junjange.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {

    // 데이터 베이스 불러오기
    @Query("SELECT * from entity")
    fun getAll(): LiveData<List<Entity>>

    // 데이터 베이스 추가
    @Insert
    fun insert(entity: Entity)



}