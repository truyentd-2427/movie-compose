package com.truyentd.moviecompose.shared.extension

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import com.truyentd.moviecompose.navigation.BaseDestination

fun NavGraphBuilder.composable(
    destination: BaseDestination,
    enterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween(300)
        )
    },
    exitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween(300)
        )
    },
    popEnterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.End,
            tween(300)
        )
    },
    popExitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.End,
            tween(300)
        )
    },
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = destination.deepLinks.map {
            navDeepLink {
                uriPattern = it
            }
        },
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}

/**
 * Navigate to provided [BaseDestination] with a Pair of key value String and Data [parcel]
 * Caution to use this method. This method use savedStateHandle to store the Parcelable data.
 * When previousBackstackEntry is popped out from navigation stack, savedStateHandle will return null and cannot retrieve data.
 * eg.Login -> Home, the Login screen will be popped from the back-stack on logging in successfully.
 */
fun NavHostController.navigate(
    destination: BaseDestination,
    parcel: Pair<String, Any?>? = null,
    builder: (NavOptionsBuilder.() -> Unit)? = null,
) {
    when (destination) {
        is BaseDestination.Up -> {
            destination.results.forEach { (key, value) ->
                previousBackStackEntry?.savedStateHandle?.set(key, value)
            }
            navigateUp()
        }

        is BaseDestination.PopUpTo -> {
            destination.results.forEach { (key, value) ->
                getBackStackEntry(destination.targetDestination.route).savedStateHandle[key] = value
            }
            popBackStack(destination.targetDestination.route, inclusive = destination.inclusive)
        }

        else -> {
            parcel?.let { (key, value) ->
                currentBackStackEntry?.savedStateHandle?.set(key, value)
            }
            if (builder != null) {
                navigate(destination.destination, navOptions(builder))
            } else {
                navigate(destination.destination)
            }
        }
    }
}

