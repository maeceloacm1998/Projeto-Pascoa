package com.marcelodev.projetopascoa.feature.config

import com.marcelodev.projetopascoa.feature.config.models.ConfigModel

sealed class ConfigViewModelEventState {
    data object OnClickHabitListener : ConfigViewModelEventState()
    data class OnClickOption(val option: ConfigModel.OptionsModel) : ConfigViewModelEventState()
    data class OnCompletedOption(val option: ConfigModel.OptionsModel) : ConfigViewModelEventState()
}