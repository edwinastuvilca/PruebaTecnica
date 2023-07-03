package com.eastuvilca.pruebatecnica.ui.main

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eastuvilca.pruebatecnica.component.TitleView

@Composable
fun MainScreen(navController: NavHostController, userName: Int?) {
    val viewModel = MainViewModel(navController)
    Scaffold(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MainView(
            Modifier
                .verticalScroll(ScrollState(0)),
            viewModel)
    }
}

@Composable
fun MainView(modifier: Modifier, viewModel: MainViewModel) {
    Column(modifier = modifier) {
        TitleView(text = "Welcome!!")
    }
}