package com.truyentd.moviecompose.domain.usecase.base

import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<Input : BaseInput, Output> {
    open val dispatchersProvider = Dispatchers.IO

    abstract fun buildUseCase(input: Input): Flow<Output>

    open operator fun invoke(input: Input): Flow<Output> {
        return buildUseCase(input).flowOn(dispatchersProvider)
    }
}

abstract class FlowNoInputUseCase<Output> {
    open val dispatchersProvider = Dispatchers.IO

    abstract fun buildUseCase(): Flow<Output>

    open operator fun invoke(): Flow<Output> {
        return buildUseCase().flowOn(dispatchersProvider)
    }
}
