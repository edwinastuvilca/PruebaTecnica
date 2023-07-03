package com.eastuvilca.pruebatecnica.ui.hi

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
import com.eastuvilca.pruebatecnica.R
import com.eastuvilca.pruebatecnica.component.*
import kotlinx.coroutines.launch

@Composable
fun HiScreen(navController: NavController){
    val viewModel = HiViewModel(navController)
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Hi(Modifier
                .align(Alignment.TopStart)
                .verticalScroll(ScrollState(0)),
            viewModel)
    }
}

@Composable
private fun Hi(modifier: Modifier, viewModel: HiViewModel){

    val email: String by viewModel.email.observeAsState(initial = "")
    val continueEnable: Boolean by viewModel.continueEnable.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier){
        TitleView("Hi!")
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
                EmailFieldCustom(
                    label = "Email",
                    value = email,
                    onValueChange = { viewModel.onEmailChanged(it) }
                )
                CustomSeparator()
                CustomButtonApp(
                    label = "Continue",
                    onClick = { coroutineScope.launch { viewModel.onContinueClick() } },
                    enabled = continueEnable
                )
                CustomSeparator()
                Text(
                    text = "or",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                CustomSeparator()
                CustomButtonSocialMedia(
                    label = "Continue With Facebook",
                    icon = R.drawable.baseline_album_24,
                    onClick = {}
                )
                CustomSeparator()
                CustomButtonSocialMedia(
                    label = "Continue With Google",
                    icon = R.drawable.baseline_account_circle_24,
                    onClick = {}
                )
                CustomSeparator()
                CustomButtonSocialMedia(
                    label = "Continue With Apple",
                    icon = R.drawable.baseline_attribution_24,
                    onClick = {}
                )
                CustomSeparator()
                Row() {
                    CustomText(
                        text = "Don't have an account? "
                    )
                    TextClickable(
                        text = "Sign up",
                        onClick = {},
                    )
                }
                CustomSeparator()
                TextClickable(text = "Forgot your password?", onClick = {})
            }
        }
    }
}

@Composable
fun EmailFieldCustom(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}