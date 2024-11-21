package com.example.electronicsstoreapp.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.electronicsstoreapp.mvi.MVI
import com.example.electronicsstoreapp.mvi.mvi
import com.example.electronicsstoreapp.main.contract.MainContract.UiState
import com.example.electronicsstoreapp.main.contract.MainContract.UiAction
import com.example.electronicsstoreapp.main.contract.MainContract.SideEffect
import com.example.electronicsstoreapp.navigation.navigator.AppNavigator
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
