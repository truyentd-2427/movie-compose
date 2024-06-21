package com.truyentd.moviecompose.navigation.auth

import com.truyentd.moviecompose.navigation.BaseDestination

sealed class AuthDestination(route: String): BaseDestination(route) {
    object Login : BaseDestination(route = "login")
}
