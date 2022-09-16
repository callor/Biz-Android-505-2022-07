package com.callor.word;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.word.helper.WordDiffUtil;
import com.callor.word.helper.WordListAdpter;
import com.callor.word.model.WordVO;
import com.callor.word.model.WordViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/*
Activity
앱을 실행했을 때 화면에 보여지는 객체
 */
public class MainActivity extends AppCompatActivity {

    // 원래 ImageView 의 Size 를 보관해둘 변수 선언
    private int imageWidth = 0;
    private int imageHeight = 0;

    private TextInputEditText txt_word;
    private WordViewModel viewModel ;

    private RecyclerView wordListView;

    /*
    안드로이드 앱이 실행될때 화면을 만들어주는 Method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        lifeCycle.ViewModelProvider 를 사용하여
        WordViewModel 클래스를 객체로 생성하여 viewModel 에 세팅하기
         */
        viewModel = new ViewModelProvider.AndroidViewModelFactory(
                MainActivity.this.getApplication()).create(
                        WordViewModel.class
        );



        // activity_main.xml 에 설정된 btn_save 위젯을 사용하기 위한
        // 객체로 설정
        Button btn_save = findViewById(R.id.btn_save);
        txt_word = findViewById(R.id.txt_word);
        wordListView = findViewById(R.id.word_list);

        WordListAdpter adapter = new WordListAdpter(new WordDiffUtil());
        wordListView.setAdapter(adapter);
        wordListView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        // adapter 와 데이터 연결
        viewModel.selectAll().observe(this,
                wordList -> adapter.submitList(wordList)
        );



        // btb_save Button 을 클릭 또는 Touch 했을때
        // 할일을 지정하기(이벤트 핸들러 설정)

        // 무명클래스, 익명클래스 방식으로 이벤트 핸들러 설정하기
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // btn_save 버튼이 클릭되면 txt_word input box 에 입력된 문자열을
                // word 변수에 담기
                String word = txt_word.getText().toString();

                Toast.makeText(view.getContext(),
                        word,
                        Toast.LENGTH_SHORT).show();

                viewModel.insert(new WordVO(0,word ));
            }
        });

    }
}