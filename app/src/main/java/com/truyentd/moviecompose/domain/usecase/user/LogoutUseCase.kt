package com.truyentd.moviecompose.domain.usecase.user

import com.truyentd.moviecompose.domain.repository.UserRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncNoInputUseCase
import com.truyentd.moviecompose.domain.usecase.base.SyncNoInputUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) : SyncNoInputUseCase<Unit>() {

    override fun buildUseCase() {
        userRepository.logout()
    }
}
