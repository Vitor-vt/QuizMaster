
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaResultado(
    pontuacao: Int,
    totalPerguntas: Int,
    voltarMenu: () -> Unit
) {
    val porcentagem = if (totalPerguntas > 0) {
        (pontuacao * 100) / totalPerguntas
    } else {
        0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultado do Quiz",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (porcentagem >= 70) {
                "Parabéns! Excelente resultado!"
            } else {
                "Continue praticando!"
            },
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "$pontuacao de $totalPerguntas",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Perguntas corretas",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        LinearProgressIndicator(
            progress = { porcentagem / 100f },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "$porcentagem% de acertos",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = voltarMenu,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar ao Menu")
        }
    }
}
