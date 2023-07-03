package com.eastuvilca.pruebatecnica.ui.login

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastuvilca.pruebatecnica.component.CustomButtonApp
import com.eastuvilca.pruebatecnica.component.CustomSeparator
import com.eastuvilca.pruebatecnica.component.DataTemplateUser
import com.eastuvilca.pruebatecnica.component.PasswordFieldCustom
import com.eastuvilca.pruebatecnica.component.TextClickable
import com.eastuvilca.pruebatecnica.component.TitleView

@Composable
fun LoginScreen(navController: NavController, data: String?) {
    val viewModel = LoginViewModel(navController, data)
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(
            Modifier
                .align(Alignment.TopStart)
                .verticalScroll(ScrollState(0)),
            viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    val password: String by viewModel.password.observeAsState(initial = "")
    val isContinueEnable: Boolean by viewModel.isContinueEnable.observeAsState(false)

    Column(modifier = modifier) {
        TitleView(text = "Log in")
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.LightGray)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.primaryVariant,
                )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                DataTemplateUser(viewModel)
                PasswordFieldCustom(
                    label = "Password",
                    value = password,
                    modelBase = viewModel,
                    onValueChange = { viewModel.onPasswordChanged(it) }
                )
                CustomSeparator()
                CustomButtonApp(
                    label = "Continue",
                    onClick = { viewModel.onContinueClick() },
                    enabled = isContinueEnable
                )
                CustomSeparator()
                CustomSeparator()
                TextClickable(
                    text = "Forgot your password?",
                    onClick = {},
                )
            }
        }
    }
}
