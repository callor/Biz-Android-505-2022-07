package com.callor.todo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;

import com.callor.todo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private NavController navController;
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



        /**
         * 화면의 상단 AppBar 에 현재 열린 fragment 정보를 표현하기
         *
         * todo_navigation.xml 에 설정된 두개의 fragment ID 정보를
         * 가져와서 AppBarConfiguration 에 설정해준다
         * AppBar 의 제목에 각각 fragment 에 설정된 label 값을 가져와서
         * 표현해 준다
         *
         */
        AppBarConfiguration appBarConfiguration
                = new AppBarConfiguration.Builder(
                        R.id.firstFragment, R.id.secondFragment
        ).build();


        /**
         * NavController 를 사용하여
         * Fragment 제어하기
         * hostFragment 객체를 생성하기
         * 
         * activity_main.xml 에 선언된
         * fragmentContainer 를 가져와서 객체 선언하기
          */
        NavHostFragment hostFragment
                = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        navController = hostFragment.getNavController();

        /**
         * fragment navigation 과 Appbar title 을 서로 연동하는
         * 코드드
        */
        NavigationUI
                .setupActionBarWithNavController(
                        this,navController,
                        appBarConfiguration);


    }


}