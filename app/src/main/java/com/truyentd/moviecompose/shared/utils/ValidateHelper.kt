package com.truyentd.moviecompose.shared.utils

import android.util.Patterns
import androidx.annotation.StringRes
import com.truyentd.moviecompose.R

object ValidateHelper {
    @StringRes
    fun validateEmail(email: String): Int? {
        return if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            R.string.email_is_invalid
        } else {
            null
        }
    }

    @StringRes
    fun validatePassword(password: String): Int? {
        return if (password.isBlank() || password.length < 6) {
            R.string.password_is_invalid
        } else {
            null
        }
    }
}
