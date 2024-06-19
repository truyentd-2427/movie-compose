package com.truyentd.moviecompose.domain.interactor.output

open class OutputObserver<Output> {
    private var onLoading: (() -> Unit)? = null
    private var onSuccess: ((Output) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null

    fun onLoading(block: () -> Unit) {
        onLoading = block
    }

    fun onSuccess(block: (Output) -> Unit) {
        onSuccess = block
    }

    fun onError(block: (Throwable) -> Unit) {
        onError = block
    }

    operator fun invoke() = onLoading?.invoke()
    operator fun invoke(output: Output) = onSuccess?.invoke(output)
    operator fun invoke(throwable: Throwable) = onError?.invoke(throwable)
}
