package com.callor.helloapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.callor.helloapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    /*
    코틀린의 객체 변수들은 원칙적으로 null 값 저장이 불가하다
    변수 type 뒤에 물음표를 붙여주면 제한적으로 null 값을
    저장할 수 있다
     */
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*
    fragment 를 확장하여 객체로 만드는 함수
    return type View?
    3개의 매개변수를 갖는 구조
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    /*
    fragment 를 화면에 그리는 함수
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        firstfragment 에 있는 buttonFirst 를 클릭하면
        어딘가에 설정된 nav controller 를 찾아서
        navigate() 함수를 실행하여 화면을 전환하라
         */
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    /*
    화면을 지우는 함수
    화면을 그리는데 사용했던 _binding 객체를
    메모리에서 제거하는 방법
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}