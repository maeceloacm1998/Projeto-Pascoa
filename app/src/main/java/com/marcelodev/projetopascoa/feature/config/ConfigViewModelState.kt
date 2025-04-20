package com.marcelodev.projetopascoa.feature.config

import com.marcelodev.projetopascoa.feature.config.models.ConfigModel


sealed interface ConfigViewModelUiState {

    data class Steps(
        val data: ConfigModel = ConfigModel(),
        val eggsFound: Int,
        val optionSelected: ConfigModel.OptionsModel?,
        val habitListenerMessage: String,
        val habitListenerGif: Int,
        val isFistHabitListenerDialog: Boolean = false,
        val isMenuHabitListenerDialog: Boolean = false,
        val isMaps: Boolean = false,
    ) : ConfigViewModelUiState
}

data class ConfigViewModelState(
    val data: ConfigModel = ConfigModel(),
    val optionSelected: ConfigModel.OptionsModel? = null,
    val eggsFound: Int = 0,
    val habitListenerMessage: String = "",
    val habitListenerGif: Int = 0,
    val isFistHabitListenerDialog: Boolean = false,
    val isMenuHabitListenerDialog: Boolean = false,
    val isMaps: Boolean = false,
) {

    fun toUiState(): ConfigViewModelUiState = ConfigViewModelUiState.Steps(
        data = data,
        eggsFound = eggsFound,
        optionSelected = optionSelected,
        habitListenerMessage = habitListenerMessage,
        habitListenerGif = habitListenerGif,
        isFistHabitListenerDialog = isFistHabitListenerDialog,
        isMenuHabitListenerDialog = isMenuHabitListenerDialog,
        isMaps = isMaps,
    )
}