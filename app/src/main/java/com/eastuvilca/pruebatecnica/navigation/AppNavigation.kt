package com.eastuvilca.pruebatecnica.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.eastuvilca.pruebatecnica.ui.hi.HiScreen
import com.eastuvilca.pruebatecnica.ui.login.LoginScreen
import com.eastuvilca.pruebatecnica.ui.main.MainScreen
import com.eastuvilca.pruebatecnica.ui.signup.SignUpScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =  AppView.hiScreen.route){
        composable(route = AppView.hiScreen.route){
            HiScreen(navController)
        }

        composable(route = AppView.signInScreen.route + "/{email}",
            arguments = listOf(
                navArgument(name = "email"){type = StringType }
            )
        ){
            SignUpScreen(navController, it.arguments?.getString("email"))
        }

        composable(route = AppView.loginScreen.route + "/{data}",
            arguments = listOf(
                navArgument(name = "data"){ type = StringType }
            )
        ){
            LoginScreen(navController, it.arguments?.getString("data"))
        }

        composable(route = AppView.mainScreen.route + "/{userid}",
            arguments = listOf(
                navArgument(name = "userid"){ type = IntType }
            )
        ){
            MainScreen(navController, it.arguments?.getInt("userid", 0))
        }
    }
}