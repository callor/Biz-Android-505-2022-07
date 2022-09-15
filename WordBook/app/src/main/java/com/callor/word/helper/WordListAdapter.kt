package com.callor.word.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.callor.word.R
import com.callor.word.model.Word

class WordListAdapter : ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word)
    }


    // 한개의 item 을 화면에 그리기 위한 준비 도구
    class WordViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val wordItemView : TextView = itemView.findViewById(R.id.word_item)

        fun bind(text:String) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent:ViewGroup) : WordViewHolder {

                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.word_item_view,parent,false)
                return WordViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word === newItem.word
        }

    }


}

