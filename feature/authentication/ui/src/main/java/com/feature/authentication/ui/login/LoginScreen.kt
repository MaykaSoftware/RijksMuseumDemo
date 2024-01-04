package com.feature.authentication.ui.login

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.core.common.constants.AuthenticationFeature
import com.core.common.constants.HomeFeature
import com.feature.authentication.ui.CustomTextField
import com.feature.authentication.ui.Dimensions.ButtonHeight
import com.feature.authentication.ui.Dimensions.ExtraLargeSpacing
import com.feature.authentication.ui.Dimensions.LargeSpacing
import com.feature.authentication.ui.Dimensions.MediumSpacing
import com.feature.authentication.ui.Dimensions.SmallSpacing
import com.feature.authentication.ui.R

@Composable
fun LoginScreen(
    modifier: Modifier,
    navController: NavController,
    uiState: LoginState,
    event: (LoginEvent) -> Unit
) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Column(
                modifier = modifier
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
                CustomTextField(
                    context = context,
                    value = uiState.email,
                    onValueChange = {
                        event(LoginEvent.UpdateEmail(it))
                    },
                    hint = context.getString(R.string.hint_email),
                    keyboardType = KeyboardType.Email,
                    isError = uiState.errorFields == ErrorFields.EMAIL,
                    errorFields = if (uiState.errorFields == ErrorFields.EMAIL) ErrorFields.EMAIL else ErrorFields.NONE
                )

                CustomTextField(
                    context = context,
                    value = uiState.password,
                    onValueChange = {
                        event(LoginEvent.UpdatePassword(it))
                    },
                    hint = context.getString(R.string.hint_password),
                    keyboardType = KeyboardType.Password,
                    isPasswordTextField = true,
                    isError = uiState.errorFields == ErrorFields.PASSWORD,
                    errorFields = if (uiState.errorFields == ErrorFields.PASSWORD) ErrorFields.PASSWORD else ErrorFields.NONE
                )

                Button(
                    onClick = { event(LoginEvent.Login(uiState.email, uiState.password)) },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(ButtonHeight),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = context.getString(R.string.btn_label_login))
                }

                GoToSignup(modifier, context) {
                    navController.navigate(AuthenticationFeature.registerScreenRoute)
                }
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(
        key1 = uiState.errorApiResponse,
        block = {
            when(uiState.errorApiResponse){
                ErrorApiResponse.NONE -> {

                }
                ErrorApiResponse.BAD_CREDENTIALS -> {
                    Toast.makeText(context, "BAD_CREDENTIALS", Toast.LENGTH_SHORT).show()
                }
                ErrorApiResponse.SERVER -> {
                    Toast.makeText(context, "SERVER", Toast.LENGTH_SHORT).show()
                }
                ErrorApiResponse.NETWORK -> {
                    Toast.makeText(context, "NETWORK", Toast.LENGTH_SHORT).show()
                }
            }
        },
    )

    LaunchedEffect(
        key1 = uiState.isLoginSuccess,
        block = {
            if(uiState.isLoginSuccess){
                Toast.makeText(context, "SUCCES", Toast.LENGTH_SHORT).show()
                navController.navigate(HomeFeature.nestedHomeRoute)
            }
        }
    )
}

@Composable
fun GoToSignup(
    modifier: Modifier = Modifier,
    context: Context,
    onNavigateToSignup: () -> Unit
) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.spacedBy(
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
            modifier = modifier.clickable { onNavigateToSignup() }
        )
    }
}