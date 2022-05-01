package com.twentyfour.bulletin_board.repository

import android.app.Application
import com.google.gson.JsonObject
import com.twentyfour.bulletin_board.data.ModelBoard
import com.twentyfour.bulletin_board.data.ModelBoardComponent
import com.twentyfour.bulletin_board.network.BoardObject
import retrofit2.Response
import java.util.ArrayList


/**
ViewMovel에서는 로컬데이터인지 원격데이터인지 신경쓰지않고 Repository를 사용할 수 있다.
result.isSuccessful : 통신에 성공했는지의 여부. 이때의 통신은 갔다 왔는가 그자체를 의미하는 것
result.body : 실질적으로 받게되는 데이터입니다. `as Type`으로 객체 타입을 명시. 여기서는 ModelBoard를 받음.

같은 시간에 여러 인스턴스가 하나의 Repository에 접근하는 것을 막기위해 싱글톤 패턴을 구현한다.
 **/


class BoardRepository(application : Application) {

    // Use Retrofit
    suspend fun retrofitSelectAllTodo(): ModelBoard {
        val response = BoardObject.getRetrofitService.getBoard()
        return if (response.isSuccessful) response.body() as ModelBoard else ModelBoard(ArrayList())

    }

    // singleton pattern
    companion object {
        private var instance: BoardRepository? = null

        fun getInstance(application : Application): BoardRepository? {
            if (instance == null) instance = BoardRepository(application)
            return instance
        }
    }

     // Insert
    suspend fun retrofitInsertTodo(modelBoardComponent: ModelBoardComponent) : Response<JsonObject> {
        return BoardObject.getRetrofitService.postBoard(modelBoardComponent.title, modelBoardComponent.contents)
    }
}