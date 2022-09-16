package com.callor.word.model;
/*
WordVO : 데이터를 담고, Table 을 생성하는 용도의 클래스
WordViewModel : ViewModel 클래스로 데이터를
Observe 방식으로 핸들링 하기 위한 도구
 */

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.callor.word.persistance.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<WordVO>> selectAll() {
        return wordRepository.selectAll();
    }

    public void insert(WordVO wordVO) {
        wordRepository.insert(wordVO);
    }
}
