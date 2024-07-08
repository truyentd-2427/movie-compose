package com.truyentd.moviecompose.shared.extension

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.getThenRemove(key: String): T? {
    if (!contains(key)) return null
    val value = get<T>(key)
    remove<T>(key)
    return value
}
