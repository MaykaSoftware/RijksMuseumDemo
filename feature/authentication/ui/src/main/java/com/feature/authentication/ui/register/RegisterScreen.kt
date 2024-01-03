package com.feature.authentication.ui.register

import android.widget.Toast
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
import com.core.common.navigation_constants.HomeFeature
import com.feature.authentication.ui.CustomTextField
import com.feature.authentication.ui.Dimensions.ButtonHeight
import com.feature.authentication.ui.Dimensions.ExtraLargeSpacing
import com.feature.authentication.ui.Dimensions.LargeSpacing
import com.feature.authentication.ui.Dimensions.MediumSpacing
import com.feature.authentication.ui.R
import com.feature.authentication.ui.login.ErrorApiResponse
import com.feature.authentication.ui.login.ErrorFields

@Composable
fun RegisterScreen(
    modifier: Modifier,
    navController: NavController,
    uiState: RegisterState,
    event: (RegisterEvent) -> Unit
){
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
                    value = uiState.username,
                    onValueChange = { event(RegisterEvent.UpdateUsername(it)) },
                    hint = context.getString(R.string.hint_username),
                    isError = uiState.errorFields == ErrorFields.USERNAME,
                    errorFields = if (uiState.errorFields == ErrorFields.USERNAME) ErrorFields.USERNAME else ErrorFields.NONE
                )

                CustomTextField(
                    context = context,
                    value = uiState.email,
                    onValueChange = { event(RegisterEvent.UpdateEmail(it)) },
                    hint = context.getString(R.string.hint_email),
                    keyboardType = KeyboardType.Email,
                    isError = uiState.errorFields == ErrorFields.EMAIL,
                    errorFields = if (uiState.errorFields == ErrorFields.EMAIL) ErrorFields.EMAIL else ErrorFields.NONE
                )

                CustomTextField(
                    context = context,
                    value = uiState.password,
                    onValueChange = { event(RegisterEvent.UpdatePassword(it)) },
                    hint = context.getString(R.string.hint_password),
                    keyboardType = KeyboardType.Password,
                    isPasswordTextField = true,
                    isError = uiState.errorFields == ErrorFields.PASSWORD,
                    errorFields = if (uiState.errorFields == ErrorFields.PASSWORD) ErrorFields.PASSWORD else ErrorFields.NONE
                )

                CustomTextField(
                    context = context,
                    value = uiState.verifyPassword,
                    onValueChange = { event(RegisterEvent.UpdateVerifyPassword(it)) },
                    hint = context.getString(R.string.hint_password),
                    keyboardType = KeyboardType.Password,
                    isPasswordTextField = true,
                    isError = uiState.errorFields == ErrorFields.VERIFY_PASSWORD,
                    errorFields = if (uiState.errorFields == ErrorFields.VERIFY_PASSWORD) ErrorFields.VERIFY_PASSWORD else ErrorFields.NONE
                )

                Button(
                    onClick = {
                        event(RegisterEvent.Register(
                            uiState.username, uiState.email, uiState.password, uiState.verifyPassword
                        ))
                    },
                    modifier = modifier
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
        key1 = uiState.isSuccessRegister,
        block = {
            if(uiState.isSuccessRegister){
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                navController.navigate(HomeFeature.nestedHomeRoute)
            }
        }
    )
}