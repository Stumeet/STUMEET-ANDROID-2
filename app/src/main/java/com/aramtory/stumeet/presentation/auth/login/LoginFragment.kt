package com.aramtory.stumeet.presentation.auth.login

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.base.BindingFragment
import com.aramtory.stumeet.coreui.fragment.toast
import com.aramtory.stumeet.coreui.view.UiState
import com.aramtory.stumeet.coreui.view.UiStateModel
import com.aramtory.stumeet.data.BaseApiPool
import com.aramtory.stumeet.data.dto.res.signup.AccessTokenResDto
import com.aramtory.stumeet.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            LoginViewModelFactory(
                kakaoLoginService = KakaoLoginService(requireContext()),
                signUpApiService = BaseApiPool.getSignUp
            )
        )[LoginViewModel::class.java]
    }

    override fun initView() {
        initLoginBtnClickListener()
        observeLoginState()
    }

    private fun initLoginBtnClickListener() {
        binding.ivLoginKakao.setOnClickListener {
            loginViewModel.kakaoLogin()
        }
    }

    private fun observeLoginState() {
        loginViewModel.loginState
            .flowWithLifecycle(lifecycle)
            .onEach { state ->
                val uiStateModel = state.getUiStateModel()
                handleUiState(uiStateModel, state)
            }
            .launchIn(lifecycleScope)
    }

    private fun handleUiState(uiStateModel: UiStateModel, state: UiState<AccessTokenResDto>) {
        when {
            uiStateModel.isLoading -> Unit
            uiStateModel.isSuccess -> handleLoginSuccess((state as UiState.Success).data)
            uiStateModel.isFailure -> handleLoginFailure((state as UiState.Failure).msg)
            uiStateModel.isEmpty -> Unit
        }
    }

    private fun handleLoginSuccess(data: AccessTokenResDto) {
        if (data.isFirstLogin) {
            navigateToProfileImage()
        } else {
            toast(" 로그인 성공")
        }
    }

    private fun handleLoginFailure(errorMessage: String) {
        toast("로그인 실패: $errorMessage")
    }

    private fun navigateToProfileImage() {
        try {
            findNavController().navigate(R.id.action_login_to_profile_image)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }
}