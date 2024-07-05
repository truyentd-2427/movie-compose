package com.truyentd.moviecompose.domain.usecase.base

import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.interactor.output.OutputObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Has input use case */

abstract class AsyncUseCase<Input : BaseInput, Output> {
    open val dispatchersProvider = Dispatchers.IO

    abstract suspend fun buildUseCase(input: Input): Output

    open suspend operator fun invoke(
        input: Input,
        block: (OutputObserver<Output>.() -> Unit)? = null,
    ) {
        val response = OutputObserver<Output>().apply { block?.invoke(this) }
        response()
        try {
            val result = withContext(dispatchersProvider) {
                buildUseCase(input)
            }
            response(result)
        } catch (throwable: Throwable) {
            response(throwable)
        }
    }
}

/** No input use case */

abstract class AsyncNoInputUseCase<Output> {
    open val dispatchersProvider = Dispatchers.IO

    abstract suspend fun buildUseCase(): Output

    open suspend operator fun invoke(block: (OutputObserver<Output>.() -> Unit)? = null) {
        val response = OutputObserver<Output>().apply { block?.invoke(this) }
        response()
        try {
            val result = withContext(dispatchersProvider) {
                buildUseCase()
            }
            response(result)
        } catch (throwable: Throwable) {
            response(throwable)
        }
    }
}
