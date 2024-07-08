package com.truyentd.moviecompose.presentation.screens

import androidx.compose.runtime.compositionLocalOf
import com.truyentd.moviecompose.domain.usecase.user.HasLoggedInUseCase
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.presentation.base.BaseViewModel
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

    val startDestination = if (hasLoggedInUseCase()) AppNavGraph.Top else AppNavGraph.Auth

    fun switchTheme() {
        _isDarkTheme.update { !it }
    }
}

val LocalAppViewModel = compositionLocalOf<AppViewModel> { error("No AppViewModel provided") }
