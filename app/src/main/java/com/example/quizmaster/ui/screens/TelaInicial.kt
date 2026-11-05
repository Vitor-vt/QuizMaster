
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaInicial(
    abrirJogador: () -> Unit,
    abrirAdmin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "QuizMaster",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Teste seus conhecimentos!",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = abrirJogador,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar como Jogador")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = abrirAdmin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar como Administrador")
        }
    }
}
