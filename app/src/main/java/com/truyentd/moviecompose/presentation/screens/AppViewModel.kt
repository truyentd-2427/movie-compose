package com.truyentd.moviecompose.presentation.screens

import androidx.compose.runtime.compositionLocalOf
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : BaseViewModel() {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme = _isDarkTheme.asStateFlow()

    val hasLoggedIn = true

    fun switchTheme(isDarkTheme: Boolean) {
        _isDarkTheme.update { isDarkTheme }
    }
}

val LocalAppViewModel = compositionLocalOf<AppViewModel> { error("No AppViewModel provided") }
