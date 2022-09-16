package com.callor.word.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.callor.word.model.WordVO;
/*
list 가 화면에 그려져 있을때
list 중 일부 item 이 변경되면  그 변경된 item 만 다시 그리도록
도와주는 class
 */
public class WordDiffUtil extends DiffUtil.ItemCallback<WordVO> {

    @Override
    public boolean areItemsTheSame(@NonNull WordVO oldItem, @NonNull WordVO newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull WordVO oldItem, @NonNull WordVO newItem) {
        return oldItem.getWord().equals(newItem.getWord());
    }
}
