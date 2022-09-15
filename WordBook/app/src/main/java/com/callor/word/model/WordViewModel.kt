package com.callor.word.model

import androidx.lifecycle.*
import com.callor.word.persistance.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel(){

    // Flow 데이터를 LiveData 로 변경
    // 한번 만들어둔 view 화면에서 데이터가 바뀌면 자동으로
    // 바뀐 데이터를 보여주게 하는 코드
    val allWords : LiveData<List<Word>> = repository.allWords.asLiveData()
    fun insert(word:Word) = viewModelScope.launch {
        repository.insert(word)
    }

}

class WordViewModelFactory(private val repository: WordRepository) 
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository) as T
        }
        throw IllegalAccessException("View Model 클래스 없음")
    }
}


