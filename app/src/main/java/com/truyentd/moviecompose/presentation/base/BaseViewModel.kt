package com.truyentd.moviecompose.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.usecase.base.AsyncNoInputUseCase
import com.truyentd.moviecompose.domain.usecase.base.AsyncUseCase
import com.truyentd.moviecompose.presentation.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.state.ErrorState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel() {
    protected val _navigator = MutableSharedFlow<BaseDestination>()
    val navigator = _navigator.asSharedFlow()

    private var loadingCount: Int = 0
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    protected val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    protected val _errorState = MutableStateFlow(ErrorState())
    val errorState = _errorState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errorState.update { it.copy(throwable = throwable, shouldShowDialog = true) }
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
                onSuccess { data ->
                    onSuccess?.invoke(data)
                    if (showLoading) {
                        hideLoading()
                    }
                }
                onError { throwable ->
                    onError?.invoke(throwable)
                    if (showLoading) {
                        hideLoading()
                    }
                    if (showError) {
                        _errorState.update {
                            it.copy(throwable = throwable, shouldShowDialog = true)
                        }
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
                onSuccess { data ->
                    onSuccess?.invoke(data)
                    if (showLoading) {
                        hideLoading()
                    }
                }
                onError { throwable ->
                    onError?.invoke(throwable)
                    if (showLoading) {
                        hideLoading()
                    }
                    if (showError) {
                        _errorState.update {
                            it.copy(throwable = throwable, shouldShowDialog = true)
                        }
                    }
                }
            }
        }
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        job: suspend () -> Unit,
    ) {
        viewModelScope.launch(context) {
            job.invoke()
        }
    }

    @CallSuper
    open fun onRefresh() {
        _isRefreshing.update { true }
    }

    protected fun showLoading() {
        if (loadingCount == 0 && !isRefreshing.value) {
            _isLoading.update { true }
        }
        loadingCount++
    }

    protected fun hideLoading() {
        loadingCount--
        if (loadingCount == 0) {
            _isRefreshing.update { false }
            _isLoading.update { false }
        }
    }

    protected fun <T> Flow<T>.handleLoading(): Flow<T> = this
        .onStart { showLoading() }
        .onCompletion { hideLoading() }

    fun dismissErrorDialog() {
        _errorState.update { it.copy(throwable = null, shouldShowDialog = false) }
    }
}
