package com.callor.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callor.todo.databinding.FragmentFirstBinding;

/**
 * findViewById 에 비해 viewBinding 방식을 사용하는 장점
 * 1. Null PointException 에서 비교적 안전하다
 * 2. xml 을 직접핸들링 하지 않기 때문에 시스템에서 좀더 여유롭다
 */
public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;

    }

    /*
    viewBinding 방식으로 Fragment 를 사용할때
    반드시 onDestroyView() method 를 정의하고
    이 method 에서 binding 객체 변수를 null 초기화하여
    메모리 누수(메모리가 불필요하게 낭비되는 현상)를 방지해야 한다.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}