package com.marcelodev.projetopascoa.components.options

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcelodev.projetopascoa.feature.config.ConfigViewModelEventState
import com.marcelodev.projetopascoa.feature.config.models.ConfigModel
import com.marcelodev.projetopascoa.ui.theme.BackgroundDialog
import com.marcelodev.projetopascoa.ui.theme.BorderDialog

@Composable
fun Options(
    modifier: Modifier = Modifier,
    optionsList: List<ConfigModel.OptionsModel> = emptyList(),
    onEvent: (ConfigViewModelEventState) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Escolha uma opção 🥕",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = BorderDialog,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        optionsList.forEach { option ->
            OptionsItem(
                title = option.title,
                isCompleted = option.isCompleted,
                onClick = {
                    onEvent(ConfigViewModelEventState.OnClickOption(option))
                },
            )
            Spacer(modifier = Modifier.padding(3.dp))
        }
    }
}

@Composable
fun OptionsItem(
    title: String,
    isCompleted: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = BackgroundDialog,
            contentColor = BorderDialog,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray,
        ),
        border = BorderStroke(2.dp, if(isCompleted) Color.Gray else BorderDialog),
        enabled = !isCompleted,
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if(isCompleted) Color.White else BorderDialog,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun OptionsPreview() {
    Options()
}