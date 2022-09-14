package com.callor.word.persistance

import androidx.annotation.WorkerThread
import com.callor.word.model.Word
import kotlinx.coroutines.flow.Flow
/*
WordDao 를 생성자로 주입받기

Room 은 별도의 thread(WorkThread)에서 모든 쿼리를 실행한다
suspend 는 coroutines 나 다른 정지 함수에서 호출하도록 한다는 선언 

 */
class WordRepository(private val wordDao: WordDao) {

    // List<Word> 의 데이터가 변경되면
    // 화면에 보여지는 View 에게 알림 전파하기
    val allWords : Flow<List<Word>> = wordDao.selectAll()

    /*
    insert 작업을 수행할때 main thread 에서 실행하지 않도록 설정
    insert 작업의 경우 생각보다 많은 시간이 소요된다
    이러한 작업을 main thread 에서 수행하면
    화면이 멈춘 것처럼 보일수 있기 때문에
    background 에서 수행하도록 설정
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word:Word) {
        wordDao.insert(word)
    }


}