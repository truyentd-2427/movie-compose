package com.truyentd.moviecompose.navigation

import androidx.navigation.NamedNavArgument

abstract class BaseDestination(val route: String = "") {
    open val arguments: List<NamedNavArgument> = emptyList()

    open val deepLinks: List<String> = listOf(
        "https://moviecompose.com/$route",
    )

    open var destination: String = route

    open var parcelableArgument: Pair<String, Any?> = "" to null


    data class Up(val results: HashMap<String, Any> = hashMapOf()) : BaseDestination() {
        fun addResult(key: String, value: Any) = apply {
            results[key] = value
        }
    }

    data class PopToBackStack(
        val targetDestination: BaseDestination,
        val inclusive: Boolean = false,
        val results: HashMap<String, Any> = hashMapOf(),
    ) : BaseDestination() {
        fun addResult(key: String, value: Any) = apply {
            results[key] = value
        }
    }
}
