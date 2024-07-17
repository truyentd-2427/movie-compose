package com.truyentd.moviecompose.domain.usecase.user

import com.truyentd.moviecompose.domain.repository.UserRepository
import com.truyentd.moviecompose.domain.usecase.base.SyncNoInputUseCase
import javax.inject.Inject

class HasLoggedInUseCase @Inject constructor(
    private val userRepository: UserRepository,
) : SyncNoInputUseCase<Boolean>() {

    override fun buildUseCase(): Boolean {
        return userRepository.hasLoggedIn()
    }
}