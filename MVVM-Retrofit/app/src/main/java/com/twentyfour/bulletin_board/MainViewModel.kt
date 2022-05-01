package com.twentyfour.bulletin_board

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.twentyfour.bulletin_board.data.ModelBoard
import com.twentyfour.bulletin_board.data.ModelBoardComponent
import com.twentyfour.bulletin_board.repository.BoardRepository
import kotlinx.coroutines.launch

/**
LiveData?
- LiveData는 observable 패턴을 사용하기에 데이터의 변화를 구독한 곳으로 통지하고, 업데이트한다.
- 메모리 누수 없는 사용을 보장한다.
- Lifecycle에 따라 LiveData의 이벤트를 제어한다.
- 항상 최신 데이터를 유지한다.
- 기기 회전이 일어나도 최신 데이터를 처리할 수 있도록 도와준다.(AAC-ViewModel과 함께 활용 시)
- LiveData의 확장도 지원한다.

MutableLiveData : 값의 get/set 모두를 할 수 있다.
LiveData : 값의 get()만을 할 수 있다.

factory를 응용하여 훨씬 적은 수고로 구상 클래스별 팩토리를 관리하여 응집성을 높이면서도
실제 사용시의 코드에서 정말 깔끔하게 클래스명만 넘기는 것으로 복잡한 별도의 factory객체를 전달하지 않아도 된다.
 **/

class MainViewModel(private val repository: BoardRepository) : ViewModel(){
    private val _retrofitTodoList = MutableLiveData<ModelBoard>()
    val retrofitTodoList: MutableLiveData<ModelBoard>
    get() = _retrofitTodoList

    init { // 초기화 시 서버에서 데이터를 받아온다.
        viewModelScope.launch {
            _retrofitTodoList.value = repository.retrofitSelectAllTodo()
        }
    }

    // insert
    fun insertRetrofit(title : String, contents : String) = viewModelScope.launch {
        val response = repository.retrofitInsertTodo(ModelBoardComponent(title, contents))
        if (response.isSuccessful) _retrofitTodoList.value = repository.retrofitSelectAllTodo()
    }

    // 하나의 팩토리로 다양한 ViewModel 클래스를 관리할 수도 있고, 원치 않는 상황에 대해서 컨트롤 할 수 있다.
    class Factory(private val application : Application) : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(BoardRepository.getInstance(application)!!) as T
        }
    }

}