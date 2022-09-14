package com.callor.word

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.callor.word.model.WordViewModel

class MainActivity : AppCompatActivity() {

    private val wordViewModel : WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}