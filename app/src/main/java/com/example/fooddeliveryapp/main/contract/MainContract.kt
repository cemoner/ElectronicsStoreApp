package com.example.fooddeliveryapp.main.contract


sealed interface MainContract {
    data class UiState(val random:String,val showBottomBar:Boolean)

    sealed interface UiAction {
        data class onClick(val route: String):UiAction
        data class OnChangeShowBottomBar(val boolean: Boolean):UiAction
    }
    sealed interface SideEffect{
        data class Navigate(val route:String) : SideEffect
    }
}