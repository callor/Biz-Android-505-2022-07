package com.callor.word.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_words")
public class WordVO {

    /*
    seq 칼럼을 PK 로 지정하고
    AUTO_INCREMENT 로 설정
     */
    @PrimaryKey(autoGenerate = true)
    private long seq;
    private String word;

    public WordVO(long seq, String word) {
        this.seq = seq;
        this.word = word;
    }

    public WordVO() {
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "WordVO{" +
                "seq=" + seq +
                ", word='" + word + '\'' +
                '}';
    }
}
