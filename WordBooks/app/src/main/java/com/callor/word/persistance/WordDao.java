package com.callor.word.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.callor.word.model.WordVO;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM tbl_words")
    public LiveData<List<WordVO>> selectAll();

    /*
    onConflict : Insert 명령을 수행할때
    만약 충돌(중복된 데이터 등)이 발생하면 어떻게 할 것인가

    OnConflictStrategy.ABORT : 처리를 중단하라
    OnConflictStrategy.FAIL : 실패처리
    OnConflictStrategy.IGNORE : 충돌 무시
    OnConflictStrategy.REPLACE : Update 를 수행하라
    OnConflictStrategy.ROLLBACK : 되돌려라
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(WordVO wordVO);


}
