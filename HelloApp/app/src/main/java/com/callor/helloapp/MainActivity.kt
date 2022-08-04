package com.callor.helloapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.callor.helloapp.databinding.ActivityMainBinding

/*
안드로드 어플은 대부분이 클래스를 상속받아 사용한다
AppCompatActivity 화면을 구현하는 중요한 클래스이다
 */
class MainActivity : AppCompatActivity() {

    /*
    코틀린은 변수를 선언과 동시에 초기화를 하는 것이 원칙이다
    lateinit 키워드를 부착하면 초기화를 뒤로 미룰 수 있다
     */
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /*
    안드로이드 어플리 시작될때 자동으로 호출되는 method
    화면이 생성될때 호출되는 event 핸들러
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        Activiti_main.xml 을 확장하여 객체변수처럼 사용할수 있도록 만드는 과정
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        /*
        확장한 xml 파일을 화면에 표현하기
         */
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        binding.fab.setOnClickListener { view ->
            /*
            화면 하단에 Alert 창을 띄우기 위한 도구
            builder pattern
            class chaining
            객체를 dot(.) 으로 연결하여 연속적으로 기능을 수행하기
             */
            // Snackbar
            //    .make(view, "안녕하세요", Snackbar.LENGTH_LONG)
            //    .show()

            // 화면하단에 Alter 창을 띄우기 위한 도구
            // 보통때는 표시되는 위치가 거의 같다
            // 하지만 화면에 키보드가 있을때는
            // Snackbar 와 Toast는 표시되는 위치가 약간 다르게 보인다
             Toast.makeText(this,"반갑습니다", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
    content_main.xml 에 선언된 fragment 를 NavController 로 등록
    navigetUp 함수를 사용하여
    화면 상단의 appBar에 fragment 메시지가 나타나도록 설정하기
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}