package com.callor.word

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.callor.word.config.WordApplication
import com.callor.word.helper.WordListAdapter
import com.callor.word.model.WordViewModel
import com.callor.word.model.WordViewModelFactory

class MainActivity : AppCompatActivity() {

    private val wordViewModel : WordViewModel by viewModels {
        WordViewModelFactory((application as WordApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // model.Word 클래스를 사용하여 데이터를 생성하고
        // insert  수행하기
        // seq 칼럼은 autoGenerator = true 로 되어 있기 때문에
        // 0을 주입하면 자동으로 증가된 seq 값이 만들어진다
        // wordViewModel.insert(Word(0,"Korea"))

        val wordListView : RecyclerView= findViewById(R.id.view_words)
        val wordListAdapter = WordListAdapter()
        wordListView.adapter = wordListAdapter
        wordListView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this) { words ->
            words?.let { wordListAdapter.submitList(it) }
        }

    }
}