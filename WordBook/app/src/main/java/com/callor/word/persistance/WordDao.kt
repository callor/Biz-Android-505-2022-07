package com.callor.word.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.callor.word.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    /*
    SQL 명령을 실행하여 List 를 SELECT 하고
    Flow 로 감싼다

    import kotlinx.coroutines.flow.Flow
     */
    @Query("SELECT * FROM tbl_words")
    fun selectAll() : Flow<List<Word>>

    /*
    @Insert 함수에서 onConflict 설정
    insert 를 수행하는데
    어떤 이유로 SQL 오류가 발생했을때
    어떻게 할 것인가를 지정하기
    OnConflictStrategy.ABORT : 무조건 중단
    OnConflictStrategy.FAIL : 실패처리
    OnConflictStrategy.IGNORE : 무시
    OnConflictStrategy.REPLACE : 덮어쓰기
    OnConflictStrategy.ROLLBACK : ROLLBACK 실행
    */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word : Word)

    @Query("DELETE FROM tbl_words")
    suspend fun deleteAll()

}