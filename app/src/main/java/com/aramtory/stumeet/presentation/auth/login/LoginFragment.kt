package com.aramtory.stumeet.presentation.auth.login

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private lateinit var loginViewModel: LoginViewModel

    override fun initView() {
        initLoginBtnClickListener()
        init()
        initKaKaoLoginObserve()
    }

    private fun init() {
        // KakaoLoginService 초기화
        val kakaoLoginService = KakaoLoginService(requireContext())

        // ViewModel 초기화
        val factory = LoginViewModelFactory(kakaoLoginService)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    // 로그인
    private fun initLoginBtnClickListener() {
        binding.ivLoginKakao.setOnClickListener {
            loginViewModel.kakaoLogin()
        }
    }

    // isKakaoLogin LiveData 관찰
    private fun initKaKaoLoginObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.isKakaoLogin.collect { isLoggedIn ->
                if (isLoggedIn) {
                    findNavController().navigate(R.id.action_login_to_profile_image)
                }
            }
        }
    }
}