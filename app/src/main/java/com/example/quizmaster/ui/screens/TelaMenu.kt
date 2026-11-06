
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
fun TelaMenu(
    abrirQuiz: () -> Unit,
    abrirRanking: () -> Unit,
    abrirHistorico: () -> Unit,
    voltar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Menu do Jogador",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Escolha o que deseja fazer!",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = abrirQuiz,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Jogar Quiz")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = abrirRanking,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ranking")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = abrirHistorico,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Historico")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = voltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
