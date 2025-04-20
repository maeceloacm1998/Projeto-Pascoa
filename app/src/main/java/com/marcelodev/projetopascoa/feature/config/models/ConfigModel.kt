package com.marcelodev.projetopascoa.feature.config.models

data class ConfigModel(
    val options: List<OptionsModel> = emptyList(),
    val fistMessage: MessageModel = MessageModel(),
    val menuMessage: MessageModel = MessageModel(),
) {
    data class OptionsModel(
        val id: String,
        val title: String,
        val message: String,
        val eggsFound: Int = 0,
        val isCompleted: Boolean = false,
    )

    data class MessageModel (
        val message: String = "",
        val gifUrl: Int = 0
    )
}