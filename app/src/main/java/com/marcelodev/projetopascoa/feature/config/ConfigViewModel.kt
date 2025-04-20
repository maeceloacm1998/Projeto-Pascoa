package com.marcelodev.projetopascoa.feature.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelodev.projetopascoa.components.habitListener.HabitListenerConstants
import com.marcelodev.projetopascoa.feature.config.ConfigViewModelEventState.OnClickHabitListener
import com.marcelodev.projetopascoa.feature.config.ConfigViewModelEventState.OnClickOption
import com.marcelodev.projetopascoa.feature.config.ConfigViewModelEventState.OnCompletedOption
import com.marcelodev.projetopascoa.feature.config.mockData.MockData
import com.marcelodev.projetopascoa.feature.config.models.ConfigModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ConfigViewModel: ViewModel() {
    private val viewModelState = MutableStateFlow(ConfigViewModelState())

    val uiState = viewModelState
        .map(ConfigViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: ConfigViewModelEventState) {
        when (event) {
            is OnClickHabitListener -> handleClickHabitListener()
            is OnClickOption -> handleClickOption(event.option)
            is OnCompletedOption -> onCompletedOption(event.option)
            is ConfigViewModelEventState.OnGoBackMenu -> onGoBackMenu()
            is ConfigViewModelEventState.OnOpenMaps -> viewModelState.value = viewModelState.value.copy(
                isMaps = true,
            )
            is ConfigViewModelEventState.OnCloseMaps -> viewModelState.value = viewModelState.value.copy(
                isMaps = false,
            )
        }
    }

    init {
        val mockData = MockData.getMockConfig()

        viewModelState.value = viewModelState.value.copy(
            data = mockData,
            eggsFound = 0,
            habitListenerMessage = mockData.fistMessage.message,
            habitListenerGif = mockData.fistMessage.gifUrl,
            isFistHabitListenerDialog = true
        )
    }

    private fun handleClickOption(option: ConfigModel.OptionsModel) {
        viewModelState.value = viewModelState.value.copy(
            optionSelected = option,
            habitListenerMessage = option.message,
            habitListenerGif = HabitListenerConstants.ImageUrl.HAPPY_HABIT_URL,
            isMenuHabitListenerDialog = false
        )
    }

    private fun handleClickHabitListener() {
        if(viewModelState.value.isFistHabitListenerDialog) {
            viewModelState.value = viewModelState.value.copy(
                isFistHabitListenerDialog = false,
                isMenuHabitListenerDialog = true,
                habitListenerMessage = viewModelState.value.data.menuMessage.message,
                habitListenerGif = viewModelState.value.data.menuMessage.gifUrl,
            )
        }
    }

    private fun onCompletedOption(option: ConfigModel.OptionsModel) {
        val data = viewModelState.value.data

        handleCompletedOptionList(option)
        viewModelState.value = viewModelState.value.copy(
            eggsFound = viewModelState.value.eggsFound + 1,
            optionSelected = null,
            habitListenerMessage = data.menuMessage.message,
            habitListenerGif = data.menuMessage.gifUrl,
            isMenuHabitListenerDialog = true
        )
    }

    private fun handleCompletedOptionList(option: ConfigModel.OptionsModel) {
        val data = viewModelState.value.data
        val options = data.options.map { item ->
            if (item.id == option.id) {
                item.copy(isCompleted = true,)
            } else {
                item
            }
        }

        viewModelState.value = viewModelState.value.copy(
            data = data.copy(options = options),
            eggsFound = viewModelState.value.eggsFound + 1,
            optionSelected = null,
            habitListenerMessage = data.menuMessage.message,
            habitListenerGif = data.menuMessage.gifUrl,
            isMenuHabitListenerDialog = true
        )
    }

    private fun onGoBackMenu() {
        viewModelState.value = viewModelState.value.copy(
            optionSelected = null,
            habitListenerMessage = viewModelState.value.data.menuMessage.message,
            habitListenerGif = viewModelState.value.data.menuMessage.gifUrl,
            isMenuHabitListenerDialog = true
        )
    }
}