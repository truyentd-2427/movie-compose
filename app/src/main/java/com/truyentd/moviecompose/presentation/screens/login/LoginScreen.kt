package com.truyentd.moviecompose.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.components.LoadingBox
import com.truyentd.moviecompose.presentation.components.textfield.AppTextField
import com.truyentd.moviecompose.presentation.components.textfield.InputWrapper
import com.truyentd.moviecompose.presentation.components.textfield.PasswordTextField
import com.truyentd.moviecompose.presentation.theme.AppColors
import com.truyentd.moviecompose.presentation.theme.AppTheme
import com.truyentd.moviecompose.shared.extension.collectAsEffect
import kotlinx.coroutines.flow.collectLatest

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(emailInput = InputWrapper(), passwordInput = InputWrapper())
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigator: ((BaseDestination) -> Unit),
) {
    viewModel.navigator.collectAsEffect { destination -> navigator(destination) }

    val emailInput by viewModel.emailInput.collectAsStateWithLifecycle()
    val passwordInput by viewModel.passwordInput.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    LoginScreenContent(
        isLoading = isLoading,
        emailInput = emailInput,
        passwordInput = passwordInput,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onLoginClick = viewModel::onLoginClick,
    )
}

@Composable
private fun LoginScreenContent(
    isLoading: Boolean = false,
    emailInput: InputWrapper,
    passwordInput: InputWrapper,
    onEmailChanged: ((String) -> Unit)? = null,
    onPasswordChanged: ((String) -> Unit)? = null,
    onLoginClick: (() -> Unit)? = null,
) {
    LoadingBox(isLoading = isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(AppColors.White)
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painterResource(R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(48.dp))
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                input = emailInput,
                placeholder = stringResource(id = R.string.email),
                onValueChange = { value ->
                    onEmailChanged?.invoke(value)
                },
                leadingIcon = {
                    Icon(Icons.Filled.Email, "")
                },
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                stringResource(id = R.string.email_description),
                modifier = Modifier.padding(horizontal = 2.dp),
                style = AppTheme.typography.body4,
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                input = passwordInput,
                placeholder = stringResource(id = R.string.password),
                onValueChange = { value ->
                    onPasswordChanged?.invoke(value)
                },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, "")
                },
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                stringResource(id = R.string.password_description),
                modifier = Modifier.padding(horizontal = 2.dp),
                style = AppTheme.typography.body4,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = {
                    onLoginClick?.invoke()
                },
            ) {
                Text(
                    stringResource(id = R.string.login),
                    style = AppTheme.typography.body2,
                    color = AppColors.White,
                )
            }
        }
    }
}
