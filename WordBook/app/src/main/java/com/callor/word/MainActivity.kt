package com.callor.word

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.callor.word.config.WordApplication
import com.callor.word.model.Word
import com.callor.word.model.WordViewModel
import com.callor.word.model.WordViewModelFactory

class MainActivity : AppCompatActivity() {

    private val wordViewModel : WordViewModel by viewModels {
        WordViewModelFactory((application as WordApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel.insert(Word(0,"Korea"))
    }
}