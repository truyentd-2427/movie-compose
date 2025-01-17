package com.truyentd.moviecompose.presentation.components.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.presentation.theme.AppColors
import com.truyentd.moviecompose.presentation.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        input = InputWrapper(),
        placeholder = "Password",
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier,
    input: InputWrapper,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: ((String) -> Unit)? = null,
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            value = input.value,
            onValueChange = { onValueChange?.invoke(it) },
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = AppColors.Black,
                disabledLabelColor = AppColors.WhiteLilac,
            ),
            shape = RoundedCornerShape(4.dp),
            leadingIcon = leadingIcon,
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Image(
                        painter = painterResource(id = if (passwordVisibility) R.drawable.ic_eye_visibility_off else R.drawable.ic_eye_visibility),
                        contentDescription = null,
                    )
                }
            },
            placeholder = { Text(placeholder) },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        )
        if (!input.isValid) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = input.errorId?.let { stringResource(id = it) }.orEmpty(),
                modifier = Modifier.padding(horizontal = 2.dp),
                style = AppTheme.typography.body4,
                color = AppColors.FlamePea,
            )
        }
    }
}
