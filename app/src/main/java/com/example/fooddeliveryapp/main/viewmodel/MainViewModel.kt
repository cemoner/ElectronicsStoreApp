package com.example.fooddeliveryapp.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.mvi.MVI
import com.example.fooddeliveryapp.mvi.mvi
import com.example.fooddeliveryapp.main.contract.MainContract.UiState
import com.example.fooddeliveryapp.main.contract.MainContract.UiAction
import com.example.fooddeliveryapp.main.contract.MainContract.SideEffect
import com.example.fooddeliveryapp.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val navigator: AppNavigator):ViewModel(),MVI<UiState,UiAction,SideEffect> by mvi(initialUiState()){
    val navigationChannel = navigator.navigationChannel

    override fun onAction(action: UiAction) {
        when (action) {
            is UiAction.onClick -> {
            }

            is UiAction.OnChangeShowBottomBar -> {
                changeShowBottomBar(action.boolean)
            }
        }
    }

    fun changeShowBottomBar(boolean: Boolean) {
        updateUiState(newUiState = uiState.value.copy(showBottomBar = boolean))
    }

}

private fun initialUiState() = UiState("random",true)
