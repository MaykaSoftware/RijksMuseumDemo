package com.core.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    value: String,
    @StringRes label: Int,
    hint: String = "",
    onValueChanged: (value: String) -> Unit,
    isPasswordField: Boolean = false,
    isClickOnly: Boolean = false,
    onClick: () -> Unit = {},
    leadingIcon: ImageVector? = null,
    @StringRes error: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onDone: () -> Unit = {},
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (isClickOnly) {
                        onClick()
                    }
                },
            value = value,
            onValueChange = { onValueChanged(it) },
            singleLine = true,
            isError = error != null,
            readOnly = isClickOnly,
            enabled = !isClickOnly,
            supportingText = {
                if (error != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(error),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            label = { Text(text = stringResource(label)) },
            placeholder = { Text(text = hint) },
            leadingIcon = {
                leadingIcon?.let {
                    Icon(it, hint, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            },
            trailingIcon = if (isPasswordField) {
                {
                    PasswordEyeIcon(isPasswordVisible = isPasswordVisible) {
                        isPasswordVisible = !isPasswordVisible
                    }
                }
            } else {
                null
            },visualTransformation = if (isPasswordField) {
                if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            } else {
                VisualTransformation.None
            },

            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onDone()
                },
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
        )
    }
}

@Composable
fun PasswordEyeIcon(
    isPasswordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit
) {
    val image = if (isPasswordVisible) {
        Icons.Filled.Visibility
    } else {
        Icons.Filled.VisibilityOff
    }

    IconButton(onClick = onPasswordVisibilityToggle) {
        Icon(image, null)
    }
}