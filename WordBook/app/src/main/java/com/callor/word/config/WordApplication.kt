package com.callor.word.config

import android.app.Application
import com.callor.word.persistance.WordDatabase
import com.callor.word.persistance.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/*
App 이 실행되는 동안에 전체적으로 사용하는 변수 등을 선언하는 클래스
 */
class WordApplication : Application() {

    // 만약 Coroutines 를 실행하는 과정에서 exception 이 발생하면
    // 다른 부분에 영향을 덜 미치도록 Coroutines 중단하라
    // 이 코드는 Application 을 상속받은 클래스에서 제일 먼저 나오는 코드
    private val appScope = CoroutineScope(SupervisorJob())

    // lazy : DB 연결이 필요한 경우에 DB Connection 을 생성하여 준비하라
    private val database by lazy { WordDatabase.getDataBase(this,appScope) }
    private val repository by lazy { WordRepository(database.wordDao())}

}