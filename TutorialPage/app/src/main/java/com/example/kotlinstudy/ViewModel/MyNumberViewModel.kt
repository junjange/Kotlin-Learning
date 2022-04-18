package com.example.kotlinstudy.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType{
    PLUS, MINUS
}

class MyNumberViewModel : ViewModel() {
    companion object{
        const val TAG : String = "로그"

    }

    // 뮤터블 라이브 데이타 - 수정 가능한 녀석
    private val _currentValue = MutableLiveData<Int>()


    init {
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0
    }

    // 변경되지 않는 데이터를 가져올 때 이름을 _ 언더바 없이 설정
    // 공개적으로 가져오는 변수는 private 이 아닌 public 으로 외부에서도 접근 가능하도록 설정
    // 하지만 값을 직접 라이브 데이타에 접근하지 않고 뷰모델을 통해 가져올 수 있도록 설정
    // 라이브 데이터 - 수정 불가능한 녀석(읽기용)
    val currentValue : LiveData<Int>
        get() = _currentValue


    fun updateValue(actionType: ActionType, input: Int){
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value =  _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value  = _currentValue.value?.minus(input)
        }
    }

}