package com.eastuvilca.pruebatecnica.ui.signup

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastuvilca.pruebatecnica.component.CustomButtonApp
import com.eastuvilca.pruebatecnica.component.CustomSeparator
import com.eastuvilca.pruebatecnica.component.CustomText
import com.eastuvilca.pruebatecnica.component.CustomTextBold
import com.eastuvilca.pruebatecnica.component.CustomTextSmall
import com.eastuvilca.pruebatecnica.component.PasswordFieldCustom
import com.eastuvilca.pruebatecnica.component.TextClickableSmall
import com.eastuvilca.pruebatecnica.component.TitleView
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController, email: String?){
    val viewModel = SignUpViewModel(navController, email!!)
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SignUp(
            Modifier
                .align(Alignment.TopStart)
                .verticalScroll(ScrollState(0)), viewModel)
    }
}

@Composable
private fun SignUp(modifier: Modifier, viewModel: SignUpViewModel){

    val userName: String by viewModel.userName.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val agreeEnable: Boolean by viewModel.agreeEnable.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier){
        TitleView("Sign up")
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.LightGray)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.primaryVariant,
                )
        ){
            Column(modifier = Modifier.padding(20.dp)){
                CustomText(
                    text = "Looks like you don't have an account. Let's create a new account for",
                )
                CustomTextBold(
                    text = viewModel.getEmail()
                )
                CustomSeparator()
                UserNameFieldCustom(
                    label = "Name",
                    value = userName,
                    onValueChange = { viewModel.onUserNameChanged(it) }
                )
                CustomSeparator()
                PasswordFieldCustom(
                    label = "Password",
                    value = password,
                    modelBase = viewModel,
                    onValueChange = { viewModel.onPasswordChanged(it) }
                )
                CustomSeparator()
                CustomButtonApp(
                    label = "Agree and Conditions",
                    onClick = { coroutineScope.launch { viewModel.onAgreeClick() } },
                    enabled = agreeEnable
                )
                CustomSeparator()
                CustomTextSmall(
                    text = "By selecting Agree and condition below."
                )
                Row() {
                    CustomTextSmall(
                        text = "I agree to "
                    )
                    TextClickableSmall(
                        text = "Terms of Service and Privacy Policy",
                        onClick = {},
                    )
                }
            }
        }
    }
}

@Composable
fun UserNameFieldCustom(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}
