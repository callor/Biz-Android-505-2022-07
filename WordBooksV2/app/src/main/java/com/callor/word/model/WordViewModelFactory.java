package com.callor.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.callor.word.persistance.WordRepository;

/*
WordViewModel 객체를 생성하고 관리하는 용도의 클래스
 */
public class WordViewModelFactory
        implements ViewModelProvider.Factory {

    private final Application application;

    public WordViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

            // return modelClass.newInstance();
            return (T) new WordViewModel(application);

    }
}
