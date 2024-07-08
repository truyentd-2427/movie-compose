package com.truyentd.moviecompose.presentation.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.data.repository.source.remote.error.ApiError

@Composable
fun AppErrorDialog(
    throwable: Throwable?,
    onDismissRequest: () -> Unit,
) {
    if (throwable is ApiError) {
        when (throwable) {
            is ApiError.NetworkError -> {
                ConfirmationDialog(
                    message = stringResource(id = R.string.network_error_message),
                    confirmButtonText = stringResource(id = R.string.close),
                    onDismissRequest = onDismissRequest,
                )
            }

            is ApiError.ServerError -> {
                ConfirmationDialog(
                    message = stringResource(id = R.string.server_error_message),
                    confirmButtonText = stringResource(id = R.string.close),
                    onDismissRequest = onDismissRequest,
                )
            }

            is ApiError.HttpError -> {
                ConfirmationDialog(
                    message = throwable.errorResponse?.message.orEmpty(),
                    confirmButtonText = stringResource(id = R.string.close),
                    onDismissRequest = onDismissRequest,
                )
            }

            else -> {
                ConfirmationDialog(
                    message = stringResource(id = R.string.unexpected_error_message),
                    confirmButtonText = stringResource(id = R.string.close),
                    onDismissRequest = onDismissRequest,
                )
            }
        }
    } else {
        // TODO Show unexpected error if needed
    }
}

@Composable
fun ConfirmationDialog(
    title: String? = null,
    message: String? = null,
    onDismissRequest: () -> Unit,
    confirmButtonText: String,
    dismissButtonText: String? = null,
    onConfirmButtonClick: (() -> Unit)? = null,
    onDismissButtonClick: (() -> Unit)? = null,
) {
    AlertDialog(
        title = {
            title?.let { Text(text = it) }
        },
        text = {
            message?.let { Text(text = it) }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmButtonClick?.invoke()
                    onDismissRequest.invoke()
                }
            ) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            dismissButtonText?.let {
                TextButton(
                    onClick = {
                        onDismissButtonClick?.invoke()
                        onDismissRequest.invoke()
                    }
                ) {
                    Text(it)
                }
            }
        }
    )
}
