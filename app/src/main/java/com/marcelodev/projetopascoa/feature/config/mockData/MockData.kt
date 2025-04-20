package com.marcelodev.projetopascoa.feature.config.mockData

import com.marcelodev.projetopascoa.components.habitListener.HabitListenerConstants
import com.marcelodev.projetopascoa.feature.config.models.ConfigModel

object MockData {
    private val mockConfig = ConfigModel(
        options = listOf(
            ConfigModel.OptionsModel(
                id = "1",
                title = "\uD83E\uDD5A Dificuldade Fácil",
                eggsFound = 3,
                message = "Os ovos estão escondidos na passarela do coelhinho, ele adora pular e dançar pela floresta. Ele adora cuidar da orta do vovô adiel, comer as cenouras que o vovô planta.\n\n Como dica, pergunte o vovô onde fica a orta.",
            ),
            ConfigModel.OptionsModel(
                id = "2",
                title = "\uD83E\uDD5A\uD83E\uDD5A Dificuldade Médio",
                eggsFound = 3,
                message = "Message 2",
            ),
            ConfigModel.OptionsModel(
                id = "3",
                title = "\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A Dificuldade Difícil",
                eggsFound = 3,
                message = "Message 3",
            ),
            ConfigModel.OptionsModel(
                id = "4",
                title = "\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A Dificuldade Impossível",
                eggsFound = 3,
                message = "Message 3",
            ),
        ),
        fistMessage = ConfigModel.MessageModel(
            message = "Olá Nina, Bernad e Liz,  vamos começar a nossa jornada?",
            gifUrl = HabitListenerConstants.ImageUrl.WALKING_HABIT_URL,
        ),
        menuMessage = ConfigModel.MessageModel(
            message = "Escolha uma opção de dificuldade para começar a caçada aos ovos de páscoa. \n\nVamos lá?",
            gifUrl = HabitListenerConstants.ImageUrl.WALKING_HABIT_URL,
        )
    )

    fun getMockConfig(): ConfigModel {
        return mockConfig
    }
}