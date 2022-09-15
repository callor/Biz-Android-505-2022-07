package com.callor.word;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.callor.word.model.WordVO;
import com.callor.word.model.WordViewModel;
import com.callor.word.model.WordViewModelFactory;

import java.util.Random;

/*
Activity
앱을 실행했을 때 화면에 보여지는 객체
 */
public class MainActivity extends AppCompatActivity {

    // 원래 ImageView 의 Size 를 보관해둘 변수 선언
    private int imageWidth = 0;
    private int imageHeight = 0;

    private WordViewModel viewModel ;

    /*
    안드로이드 앱이 실행될때 화면을 만들어주는 Method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        ViewModel 활용하여 tbl_words 데이터베이스로 부터 SELECT All 수행하라
         */
//        viewModel = new ViewModelProvider.AndroidViewModelFactory(
//                MainActivity.this.getApplication()).create(WordViewModel.class);

        viewModel = new WordViewModelFactory(this.getApplication())


        viewModel.selectAll().observe(this,
                wordList -> {
                for(WordVO word : wordList) {
                    Log.d("MAIN", word.getWord());
                }
        });


        // activity_main.xml 에 설정된 btn_size 위젯을 사용하기 위한
        // 객체로 설정
        Button btn_size = findViewById(R.id.btn_size);


        // btb_size Button 을 클릭 또는 Touch 했을때
        // 할일을 지정하기(이벤트 핸들러 설정)

        // 무명클래스, 익명클래스 방식으로 이벤트 핸들러 설정하기
        btn_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Button Click",
                        Toast.LENGTH_SHORT).show();

                String word = String.format("Korea %d",
                        Math.floor(Math.random() * 100),0);

                WordVO wordVO = new WordVO(0,word);
                viewModel.insert(wordVO);

                // ImageView 객체 선언
                ImageView imageView = findViewById(R.id.image);

                // ConstraintLayout 에 포함된
                // ImageView 의 layout 정보를 가져오기
                // import android.widget.ConstraintLayout;
                ConstraintLayout.LayoutParams params
                        = (ConstraintLayout.LayoutParams)
                        imageView.getLayoutParams();

                if(imageWidth == 0) {
                    imageWidth = params.width;
                    imageHeight = params.height;
                }

                if(imageWidth == params.width) {
                    // ImageView 의 width 와 height 를 변경하기
                    params.width = 1000; // metrics.widthPixels;
                    params.height = 1400; // metrics.heightPixels;
                } else {
                    params.width = imageWidth;
                    params.height = imageHeight;
                }

                // 변경된 정보를 적용하기
                imageView.setLayoutParams(params);

            }
        });

    }
}