package com.eastuvilca.pruebatecnica.navigation

sealed class AppView(val route: String){
    object hiScreen: AppView("hi_view")
    object signInScreen: AppView("sing_in_view")
    object loginScreen: AppView("login_view")
    object mainScreen: AppView("main_view")
}