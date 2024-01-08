package com.feature.authentication.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.core.theme.components.AppTextField
import com.feature.authentication.ui.Dimensions.ButtonHeight
import com.feature.authentication.ui.Dimensions.ExtraLargeSpacing
import com.feature.authentication.ui.Dimensions.LargeSpacing
import com.feature.authentication.ui.Dimensions.MediumSpacing
import com.feature.authentication.ui.R

@Composable
internal fun RegisterScreen(
    viewModel: RegisterViewModel,
    onAuthSuccess: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    when (val state = uiState.value) {
        RegisterUiState.Authenticated -> {
            onAuthSuccess()
        }

        is RegisterUiState.Default -> {
            Register(
                uiState = state,
                onEvent = { event ->
                    viewModel.onEvent(event)
                }
            )
        }
    }
}

@Composable
fun Register(
    uiState: RegisterUiState.Default,
    onEvent: (RegisterEvent) -> Unit
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(
                        color = if (isSystemInDarkTheme()) {
                            MaterialTheme.colorScheme.background
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    )
                    .padding(
                        top = ExtraLargeSpacing + LargeSpacing,
                        start = LargeSpacing + MediumSpacing,
                        end = LargeSpacing + MediumSpacing,
                        bottom = LargeSpacing
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LargeSpacing)
            ) {

                AppTextField(
                    value = uiState.fullName,
                    label = R.string.full_name,
                    hint = "John Doe",
                    error = uiState.fullNameError,
                    leadingIcon = Icons.Filled.Person,
                    onValueChanged = { onEvent(RegisterEvent.FullNameChanged(it)) },
                    imeAction = ImeAction.Next,
                )

                AppTextField(
                    value = uiState.email,
                    label = R.string.email,
                    hint = "yourname@domain.com",
                    error = uiState.emailError,
                    leadingIcon = Icons.Filled.Email,
                    onValueChanged = { onEvent(RegisterEvent.EmailChanged(it)) },
                    imeAction = ImeAction.Next,
                )

                AppTextField(
                    value = uiState.password,
                    label = R.string.password,
                    hint = "your password",
                    error = uiState.passwordError,
                    leadingIcon = Icons.Filled.Lock,
                    isPasswordField = true,
                    onValueChanged = { onEvent(RegisterEvent.PasswordChanged(it)) },
                    imeAction = ImeAction.Next,
                )

                AppTextField(
                    value = uiState.passwordConfirm,
                    label = R.string.confirm_password,
                    hint = "same password again",
                    error = uiState.passwordConfirmError,
                    leadingIcon = Icons.Filled.Lock,
                    isPasswordField = true,
                    onValueChanged = { onEvent(RegisterEvent.ConfirmPasswordChanged(it)) },
                )

                Button(
                    onClick = {
                        onEvent(RegisterEvent.Register)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ButtonHeight),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = context.getString(R.string.btn_label_signup))
                }

            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }
    }
}