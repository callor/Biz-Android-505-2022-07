package com.callor.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.callor.todo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 기존의 setContentView 코드를 viewBinding 방식으로 변경한다
        // setContentView(R.layout.activity_main);
        // TextView txtView = findByViewId(R.id.txt);
        // txtView.setText("대한민국")

        // activity_main.xml 을 확장하여 객체로 생성하기
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        // mainBinding.txt.setText("대한민국");

    }
}