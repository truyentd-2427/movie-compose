package com.truyentd.moviecompose.navigation.auth

import com.truyentd.moviecompose.navigation.AppDestination

sealed class AuthDestination(route: String): AppDestination(route) {
    object Login : AppDestination(route = "login")
}