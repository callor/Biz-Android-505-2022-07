package com.callor.word.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.callor.word.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 추상클래스
// 인터페이스는 implement 를 하면 인터페이스에 정의된
// 모든 함수(method) 를 반드시 재 정의해야 한다
// 인터페이스를 상속받은 클래스에 abstract 키워드를 부착하면
// 필요한 method 만 재 정의하여 사용할 수 있다
// 일부 함수는 parents 에서 정의하고
// 상속받은 곳에서 그대로 사용하거나, 또는 재정의하여 사용할 수 있다
@Database(entities = arrayOf(Word::class),
    version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    // 추상 메서드
    // 내용 코드가 없는 함수
    // 모양만 정의된 함수
    abstract fun wordDao() : WordDao

    // Java 에서 static 클래스에 해당하는 키워드
    companion object {

        // DB 연결을 위한 커넥션 생성하기 시작
        private var DB_CONN : WordDatabase? = null
        fun getDataBase (
            context : Context,
            scope : CoroutineScope
        ) : WordDatabase {
            return  DB_CONN ?: synchronized(this) {
                // Room.dataBaseBuilder 를 사용하여
                // DBMS 와 연결하는 커넥션 만들기

                val conn = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    // DBMS 와 연결되는 커넥션의 별명을
                    // WordDB 로 한다
                    "WordDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                // 생성한 커넥션을 DB_CONN 에 저장하고
                DB_CONN = conn
                // 밖으로 return 하기
                conn
            }
        } // end getDataBase

    }

}