package com.callor.word.persistance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.callor.word.model.WordVO;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
이 클래스에서 abstract 키워드의 용도
RoomDatabase 를 상속받았을때 반드시 재 정의해야할 method 가 있는데
그 설정을 무시하고, 필요한 method 만 재 정의 하겠다 라는 선언
 */
@Database(entities = {WordVO.class},
        version = 1,
        exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    /*
    이 method 에서 abstract 키워드의 용도
    만약 WordDatabase 클래스를 상속받는 곳에서는 이 method 는
    반드시 재 정의하라
     */
    public abstract WordDao wordDao();

    /*
    프로그램이 작동되는 과정에서 사용된 변수를 저장하는 메모리는
    운영체제, JVM 등에서 최적화를 수행하며 직접 관리를 한다
    volatile 로 지정된 메모리는 최적화에서 제외하고
    성능에 우선을 두는 메모리 관리를 수행하라 라는 의미

    java 에서 volatile 선언
    JVM 메모리가 아닌 안드로이드 OS 가 관리하는 곳에 변수를 저장하라라
    */
    private static volatile WordDatabase DB_CONN;

    // 이 변수 선언문 이후의 method 는
    // thread 환경에서 background 로 실행하라
    public static final ExecutorService databaseExcutor
            = Executors.newFixedThreadPool(3);

    /*
    SingleTone 객체 선언
    프로젝트가 실행되는 과정에서 한번만 객체를 만들고
    그 객체를 재활용 하는 방식
     */
    public static WordDatabase getInstance(final Context context) {
        if(DB_CONN == null) {
            synchronized (WordDatabase.class) {
                if(DB_CONN == null) {
                    DB_CONN = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordDatabase.class,"wordDB"
                    ).build();
                }
            }
        }
        return DB_CONN;
    }

}
