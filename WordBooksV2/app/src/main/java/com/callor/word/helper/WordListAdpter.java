package com.callor.word.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.word.R;
import com.callor.word.model.WordVO;

/*
1. ListAdapter 를 선언하고
2. 내부 클래스로 ListHoder 를 선언
3. ListHoder 는 RecyclerView.ViewHolder 를 상속받는다
4. WordListAdapter 는 ListAdapter 를 상속받는다
5. ListAdapter 에 VO 클래스와 ListHolder 클래스를 Generic 으로 선언한다

 */
public class WordListAdpter extends ListAdapter<WordVO, WordListAdpter.WordListHolder> {

    public WordListAdpter(@NonNull DiffUtil.ItemCallback<WordVO> diffCallback) {
        super(diffCallback);
    }

    protected WordListAdpter(@NonNull AsyncDifferConfig<WordVO> config) {
        super(config);
    }

    @NonNull
    @Override
    public WordListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // item 을 표현하기 위한 word_item.xml 을 Holder 에게 보내기 위한 준비
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_item,parent,false);

        WordListHolder holder = new WordListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordListHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public class WordListHolder extends RecyclerView.ViewHolder {

        TextView txt_word_item;

        // itemView 의 역할 : word_item.xml 을 대신하는 객체
        public WordListHolder(@NonNull View itemView) {
            super(itemView);
            // word_item.xml 에 선언된 TextView 를 객체로 선언하기
            txt_word_item = itemView.findViewById(R.id.txt_word_item);
        }
        // word 문자열 데이터를 받아서 TextView 에 세팅하기
        public void bind(WordVO wordVO) {
            txt_word_item.setText(wordVO.getWord());
        }

    }

}
