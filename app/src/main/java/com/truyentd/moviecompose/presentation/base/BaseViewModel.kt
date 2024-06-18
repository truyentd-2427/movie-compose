package com.truyentd.moviecompose.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.usecase.base.AsyncNoInputUseCase
import com.truyentd.moviecompose.domain.usecase.base.AsyncUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

open class BaseViewModel : ViewModel() {
    private var loadingCount: Int = 0

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    protected val _error = MutableSharedFlow<Throwable>()
    val error = _error.asSharedFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _error.tryEmit(throwable)
    }

    protected val scope = viewModelScope.plus(exceptionHandler)

    fun <I : BaseInput, O> launchUseCase(
        baseUseCase: AsyncUseCase<I, O>,
        input: I,
        showLoading: Boolean = true,
        showError: Boolean = true,
        onLoading: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((O) -> Unit)? = null
    ): Job {
        return scope.launch {
            baseUseCase(input) {
                onLoading {
                    onLoading?.invoke()
                    if (showLoading) {
                        showLoading()
                    }
                }
                onSuccess {
                    onSuccess?.invoke(it)
                    if (showLoading) {
                        hideLoading()
                    }
                }
                onError {
                    onError?.invoke(it)
                    if (showLoading) {
                        hideLoading()
                    }
                    if (showError) {
                        _error.tryEmit(it)
                    }
                }
            }
        }
    }

    fun <O> launchUseCase(
        baseUseCase: AsyncNoInputUseCase<O>,
        showLoading: Boolean = true,
        showError: Boolean = true,
        onLoading: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((O) -> Unit)? = null
    ): Job {
        return scope.launch {
            baseUseCase {
                onLoading {
                    onLoading?.invoke()
                    if (showLoading) {
                        showLoading()
                    }
                }
                onSuccess {
                    onSuccess?.invoke(it)
                    if (showLoading) {
                        hideLoading()
                    }
                }
                onError {
                    onError?.invoke(it)
                    if (showLoading) {
                        hideLoading()
                    }
                    if (showError) {
                        _error.tryEmit(it)
                    }
                }
            }
        }
    }

    protected fun showLoading() {
        if (loadingCount == 0) {
            _isLoading.value = true
        }
        loadingCount++
    }

    protected fun hideLoading() {
        loadingCount--
        if (loadingCount == 0) {
            _isLoading.value = false
        }
    }
}
