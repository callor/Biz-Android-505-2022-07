package com.callor.word.persistance;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.callor.word.model.WordVO;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    public WordRepository(Application application) {
        WordDatabase db = WordDatabase.getInstance(application);
        wordDao = db.wordDao();
    }

    public LiveData<List<WordVO>> wordList() {
        return wordDao.selectAll();
    }

    public void insert(WordVO wordVO) {
        // insert 를 수행할때 background 에서 수행하라
        WordDatabase.databaseExcutor.execute(
                ()-> wordDao.insert(wordVO));
    }


}
