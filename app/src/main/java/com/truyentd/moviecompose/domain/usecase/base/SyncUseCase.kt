package com.truyentd.moviecompose.domain.usecase.base

import com.truyentd.moviecompose.domain.interactor.input.BaseInput

abstract class SyncUseCase<Input : BaseInput, Output> {
    abstract fun buildUseCase(input: Input): Output

    open operator fun invoke(input: Input): Output {
        return buildUseCase(input)
    }
}

abstract class SyncNoInputUseCase<Output> {
    abstract fun buildUseCase(): Output

    open operator fun invoke(): Output {
        return buildUseCase()
    }
}
