package com.example.dailymemo.Auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.dailymemo.R
import com.example.dailymemo.Service.LoginService
import com.example.dailymemo.Setting.Dialog.SampleDialog
import com.example.dailymemo.databinding.FragmentLoginBinding


class LoginFragment : Fragment(),LoginView {

    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)

        binding.loginfragLoginBtn.setOnClickListener{
            login()
        }
        binding.loginfragRegisterTv.setOnClickListener{
            findNavController().navigate(R.id.signUpFragment)
        }
        binding.loginfragSearchingIdTv.setOnClickListener {
            val searchingFragment = SearchingFragment(0)
            binding.loginfragLoginBtn.visibility = View.GONE

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.login_fragment_view, searchingFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.loginfragSearchingPwTv.setOnClickListener {
            val searchingFragment = SearchingFragment(1)
            binding.loginfragLoginBtn.visibility = View.GONE
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            transaction.replace(R.id.login_fragment_view, searchingFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.apply {
            loginfragIdEt.addTextChangedListener (object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val inputText = s.toString()
                    if(inputText.isNotEmpty()) {
                        loginfragLoginBtn.setBackgroundDrawable(resources.getDrawable(R.drawable.point_btn_active_layout))
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })

            binding.loginfragPwEt.addTextChangedListener (object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val inputText = s.toString()
                    if(inputText.isNotEmpty()) {
                        loginfragLoginBtn.setBackgroundDrawable(resources.getDrawable(R.drawable.point_btn_active_layout))
                    }
                    else {
                        loginfragLoginBtn.setBackgroundDrawable(resources.getDrawable(R.drawable.point_color_25_5_btn_layout))
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    fun login(){
        val loginService = LoginService()
        loginService.setLoginView(this)

        val id_text = binding.loginfragIdEt.text.toString()
        val pw_text = binding.loginfragPwEt.text.toString()

        loginService.login(id_text,pw_text)
    }

    override fun loginSuccess(){
        findNavController().navigate(R.id.openStreamFragment)
    }

    override fun loginFailed() {
        val dialog = SampleDialog(requireContext(), "아이디 또는 비밀번호가 잘못되었습니다.")
        dialog.show()
    }

}