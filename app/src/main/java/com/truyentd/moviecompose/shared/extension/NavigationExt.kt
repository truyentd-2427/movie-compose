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
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import com.truyentd.moviecompose.presentation.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.navigation.NavigationType

inline fun <reified T : BaseDestination> NavGraphBuilder.composableX(
    deepLinks: List<String> = emptyList(),
    noinline enterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween(300)
        )
    },
    noinline exitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween(300)
        )
    },
    noinline popEnterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.End,
            tween(300)
        )
    },
    noinline popExitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.End,
            tween(300)
        )
    },
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        deepLinks = deepLinks.map {
            navDeepLink { uriPattern = it }
        },
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}

/**
 * Navigate to provided [BaseDestination].
 * Caution to use this method. This method use savedStateHandle to store the Parcelable data.
 * When previousBackstackEntry is popped out from navigation stack, savedStateHandle will return null and cannot retrieve data.
 * eg.Login -> Home, the Login screen will be popped from the back-stack on logging in successfully.
 */
fun NavHostController.navigateX(
    destination: BaseDestination,
    navOptions: (NavOptionsBuilder.() -> Unit)? = null,
) {
    when (destination) {
        is NavigationType.NavigateUp -> {
            destination.results?.forEach { (key, value) ->
                previousBackStackEntry?.savedStateHandle?.set(key, value)
            }
            navigateUp()
        }

        is NavigationType.PopBackStack<*> -> {
            destination.results?.forEach { (key, value) ->
                getBackStackEntry(destination).savedStateHandle[key] = value
            }
            popBackStack(
                route = destination,
                inclusive = destination.inclusive,
                saveState = destination.saveState,
            )
        }

        is AppRoute -> {
            if (navOptions != null) {
                navigate(destination, androidx.navigation.navOptions(navOptions))
            } else {
                navigate(destination)
            }
        }
    }
}
