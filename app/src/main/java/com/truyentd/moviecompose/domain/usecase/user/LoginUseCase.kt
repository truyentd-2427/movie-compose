package com.truyentd.moviecompose.domain.usecase.user

import com.truyentd.moviecompose.domain.repository.UserRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncNoInputUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
) : AsyncNoInputUseCase<Unit>() {

    override suspend fun buildUseCase() {
        userRepository.login()
    }
}
