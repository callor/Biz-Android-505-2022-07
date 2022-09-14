package com.callor.word.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
data class
Kotlin 의 독특한 class type 의 한가지로
VO(DTO) 클래스를 선언하는 명령문

class 클래스(  기본 생성자 )
 */
@Entity(tableName = "tbl_words")
data class Word(
    // seq 칼럼을 PK 로 선언하고 AUTO_INCREMENT 속성 부여
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="seq")
    val seq : Long,

    /*
    var : 값을 변경할수 있는 변수 선언
    val : 한번 값을 저장하면 변경할수 없는 변수
        : final 선언과 같다
     */
    @ColumnInfo(name="word")
    val word : String

)