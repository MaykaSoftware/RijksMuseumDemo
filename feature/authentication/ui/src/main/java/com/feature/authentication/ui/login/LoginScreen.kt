package com.feature.authentication.ui.login

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.feature.authentication.ui.Dimensions.SmallSpacing
import com.feature.authentication.ui.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onAuthSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()
    when (val state = uiState.value) {
        LoginUiState.Authenticated -> {
            LaunchedEffect(Unit) { onAuthSuccess() }
        }

        is LoginUiState.NotAuthenticated -> {
            Login(
                uiState = state,
                onEvent = { event ->
                    viewModel.onEvent(event)
                    if (event is LoginUiEvent.Signup) {
                        onNavigateToSignup()
                    }
                },
            )
        }

        is LoginUiState.AuthenticationError -> {
            // @Todo display error message
        }
    }
}

@Composable
fun Login(
    uiState: LoginUiState.NotAuthenticated,
    onEvent: (LoginUiEvent) -> Unit
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
                    value = uiState.email,
                    label = R.string.hint_email,
                    hint = "yourname@domain.com",
                    error = uiState.emailError,
                    leadingIcon = Icons.Filled.Email,
                    onValueChanged = { onEvent(LoginUiEvent.EmailChanged(it)) },
                    imeAction = ImeAction.Next,
                )

                AppTextField(
                    value = uiState.password,
                    label = R.string.hint_password,
                    hint = "your password",
                    error = uiState.passwordError,
                    leadingIcon = Icons.Filled.Lock,
                    isPasswordField = true,
                    onValueChanged = { onEvent(LoginUiEvent.PasswordChanged(it)) },
                )

                Button(
                    onClick = { onEvent(LoginUiEvent.Login) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ButtonHeight),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = context.getString(R.string.btn_label_login))
                }

                GoToSignup( context) {
                    onEvent(LoginUiEvent.Signup)
                }
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun GoToSignup(
    context: Context,
    onNavigateToSignup: () -> Unit
) {
    Row(
        modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(
            SmallSpacing
        )
    ) {
        Text(
            text = context.getString(R.string.label_no_account),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = context.getString(R.string.label_signup),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onNavigateToSignup() }
        )
    }
}