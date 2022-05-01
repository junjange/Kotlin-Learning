package com.twentyfour.bulletin_board

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.twentyfour.bulletin_board.data.ModelBoard
import com.twentyfour.bulletin_board.repository.BoardRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: BoardRepository) : ViewModel(){
    private val _retrofitTodoList = MutableLiveData<ModelBoard>()
    val retrofitTodoList: MutableLiveData<ModelBoard>
    get() = _retrofitTodoList

    init { // 초기화 시 서버에서 데이터를 받아옵니다.
        viewModelScope.launch {
            _retrofitTodoList.value = repository.retrofitSelectAllTodo()
        }
    }


    class Factory(private val application : Application) : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(BoardRepository.getInstance(application)!!) as T
        }
    }









}