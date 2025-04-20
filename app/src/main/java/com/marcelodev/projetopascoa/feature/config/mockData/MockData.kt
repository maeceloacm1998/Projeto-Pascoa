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
                message = "Não sou bicho, mas tenho patas.\n" +
                        "Não sou gente, mas fico de pé.\n" +
                        "Você se senta em mim, mas não sou sofá.\n" +
                        "Use o mapa para te ajudar a encontrar.",
            ),
            ConfigModel.OptionsModel(
                id = "2",
                title = "\uD83E\uDD5A\uD83E\uDD5A Dificuldade Médio",
                eggsFound = 3,
                message = "Sou fria por dentro, quente por fora.\n" +
                        "Guardo o que te dá energia toda hora.\n" +
                        "Quando você quer suco ou sobremesa,\n" +
                        "É em mim que você corre, com certeza!\n" +
                        "Use o mapa para te ajudar a encontrar.",
            ),
            ConfigModel.OptionsModel(
                id = "3",
                title = "\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A Dificuldade Difícil",
                eggsFound = 3,
                message = "Na sala eu fico, pra descansar,\n" +
                        "Gente senta em mim pra conversar.\n" +
                        "Use o mapa para te ajudar a encontrar.",
            ),
            ConfigModel.OptionsModel(
                id = "4",
                title = "\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A Dificuldade Impossível",
                eggsFound = 3,
                message = "Tenho folhas, tenho galhos,\n" +
                        "E às vezes, um ninho de passarinhos!\n" +
                        "Meus filhos são laranjas e azedinhos.\n" +
                        "Use o mapa para te ajudar a encontrar.",
            ),
            ConfigModel.OptionsModel(
                id = "5",
                title = "\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A Dificuldade Impossível[++]",
                eggsFound = 3,
                message = "Tenho folhas, tenho galhos,\n" +
                        "E às vezes, um ninho de passarinhos!\n" +
                        "Meus filhos são laranjas e azedinhos.\n" +
                        "Use o mapa para te ajudar a encontrar.",
            ),
            ConfigModel.OptionsModel(
                id = "6",
                title = "\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A\uD83E\uDD5A Dificuldade Impossível[+++]",
                eggsFound = 3,
                message = "Sou grande e redonda, docinha de montão,\n" +
                        "Nasço de um pé, bem perto do chão.\n" +
                        "Tenho casca verde e vermelha por dentro,\n" +
                        "Me ache no jardim, bem no centro!\n" +
                        "Use o mapa para te ajudar a encontrar.",
            ),
        ),
        fistMessage = ConfigModel.MessageModel(
            message = "Olá Nina, Bernad e Liz,  vamos começar a nossa jornada?",
            gifUrl = HabitListenerConstants.ImageUrl.WALKING_HABIT_URL,
        ),
        menuMessage = ConfigModel.MessageModel(
            message = "Escolha uma opção de dificuldade para começar a caçada aos ovos de páscoa. \n\n" +
                    "Vamos lá?",
            gifUrl = HabitListenerConstants.ImageUrl.WALKING_HABIT_URL,
        )
    )

    fun getMockConfig(): ConfigModel {
        return mockConfig
    }
}