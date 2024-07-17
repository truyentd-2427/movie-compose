package com.truyentd.moviecompose.presentation.components.textfield

import android.os.Parcelable
import androidx.annotation.StringRes
import com.truyentd.moviecompose.shared.utils.ValidateHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(
    val value: String = "",
    @StringRes
    val errorId: Int? = null,
) : Parcelable {
    val isValid: Boolean
        get() = errorId == null

    fun updateValue(value: String, shouldClearError: Boolean = true): InputWrapper {
        return copy(value = value, errorId = if (shouldClearError) null else errorId)
    }

    fun validate(block: ValidateHelper.(String) -> Int?): InputWrapper {
        return copy(errorId = block.invoke(ValidateHelper, value))
    }
}
