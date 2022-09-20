package com.callor.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callor.todo.databinding.FragmentFirstBinding;

import java.util.List;

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

        /**
         * ImageView 에 src 를 설정하는데
         * src 에 설정된 drawable 파일에는
         * 2개의 icon 을 설정했다
         * 2개 중에 한개의 icon state_pressed 를 true 로 설정하였다
         * ImageView 에 click Event 를 설정하면
         * 버튼을 터치 한 상태에서 2개의 아이콘이 번갈아 표시된다
         */
        binding.mikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation
//                        .findNavController(view)
//                        .navigate(R.id.action_firstFragment_to_secondFragment);

                // 안드로이드 폰데 내장된 음성인식 기능을 사용하기 위한 준비
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                // 한글등 언어에 관계없이 인식하여 달라
                intent.putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                // STT 가 실행되었을때 화면에 Prompt 를 띄우고 기다려라
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"말을 시작하세요");

                // 지금부터 시작
                activityResult.launch(intent);


            }
        });



        return view;

    } // onCreateView 가 끝나는곳

    // STT 에게 음성인식을 시킨 후 Return 되는 문자열을 수신할 Callback 도구
    private ActivityResultLauncher<Intent> activityResult
            = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                // 외부 Activity 를 호출하고
                // 그 Activity 가 보내주는 데이터를 수신하는 method
                @Override
                public void onActivityResult(ActivityResult result) {

                    // 정상적으로 음성인식이 되어 text 가 return 되면
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        List<String> strText = result
                                .getData()
                                .getStringArrayListExtra(
                                        RecognizerIntent.EXTRA_RESULTS
                                );
                        binding.txtView.setText(strText.get(0));
                    }
                }
            }
    );







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