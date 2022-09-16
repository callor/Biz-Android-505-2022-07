package com.callor.word.persistance;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.callor.word.model.WordVO;
import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<WordVO>> wordList;

    public WordRepository(Application application) {
        WordDatabase db = WordDatabase.getInstance(application);
        wordDao = db.wordDao();
        wordList = wordDao.selectAll();
    }

    public LiveData<List<WordVO>> selectAll() {
        return wordDao.selectAll();
    }

    public void insert(WordVO wordVO) {
        /*
         insert SQL 은 실제로 시간이 많이 소요되고
         오류가 발생할 확률이 높기 때문에
         수행할때 background 에서 수행하라

         JS 에서 함수의 매개변수로 함수를 전달하는 코드
         onClick={ ()=>onClickHandler() }
         onClick={ onClickHandler }

         java 1.8 이상에서는 자바의 method 를 다른 method 에
         전달할 수 있다

         execute() method 에게 wordDao.insert(데이터) method 를 보내서
         execute() 가 wordDao.insert() method 를 실행하도록 지시하기

         ()->wordDao.insert(wordVO) : lambda 코드로 method 변환시켜서
         execute() method 에게 전달하고 대신 실행하도록 지시하기

         */
        WordDatabase.databaseExcutor.execute(
                ()-> wordDao.insert(wordVO)
        );
    }


}
