package com.truyentd.moviecompose.presentation.screens.login

import androidx.lifecycle.SavedStateHandle
import com.truyentd.moviecompose.domain.usecase.user.LoginUseCase
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import com.truyentd.moviecompose.presentation.components.textfield.InputWrapper
import com.truyentd.moviecompose.shared.constant.KEY_EMAIL
import com.truyentd.moviecompose.shared.constant.KEY_PASSWORD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    val emailInput = savedStateHandle.getStateFlow(KEY_EMAIL, InputWrapper())
    val passwordInput = savedStateHandle.getStateFlow(KEY_PASSWORD, InputWrapper())

    fun onEmailChanged(email: String) {
        savedStateHandle[KEY_EMAIL] = emailInput.value.updateValue(email)
    }

    fun onPasswordChanged(password: String) {
        savedStateHandle[KEY_PASSWORD] = passwordInput.value.updateValue(password)
    }

    fun onLoginClick() {
        savedStateHandle[KEY_EMAIL] = emailInput.value.validate { validateEmail(it) }
        savedStateHandle[KEY_PASSWORD] = passwordInput.value.validate { validatePassword(it) }
        if (!areInputsValid()) return
        launchUseCase(loginUseCase) {
            launch { _navigator.emit(AppNavGraph.Top) }
        }
    }

    private fun areInputsValid(): Boolean {
        return emailInput.value.isValid && passwordInput.value.isValid
    }
}
