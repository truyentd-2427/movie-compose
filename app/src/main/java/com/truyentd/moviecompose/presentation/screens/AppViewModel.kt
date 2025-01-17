package com.truyentd.moviecompose.presentation.screens

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.compositionLocalOf
import com.truyentd.moviecompose.domain.usecase.user.HasLoggedInUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import com.truyentd.moviecompose.presentation.model.SnackbarData
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    hasLoggedInUseCase: HasLoggedInUseCase,
) : BaseViewModel() {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme = _isDarkTheme.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<SnackbarData?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()

    val startDestination = if (hasLoggedInUseCase()) AppRoute.TopGraph else AppRoute.AuthGraph

    fun switchTheme() {
        _isDarkTheme.update { !it }
    }

    fun showSnackbar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        launch { _snackbarMessage.emit(SnackbarData(message = message, duration = duration)) }
    }
}

val LocalAppViewModel = compositionLocalOf<AppViewModel> { error("No AppViewModel provided") }
