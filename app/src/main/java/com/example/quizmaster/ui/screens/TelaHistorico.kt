
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PartidaHistorico(
    val data: String,
    val acertos: Int,
    val totalPerguntas: Int
)

@Composable
fun TelaHistorico(
    voltar: () -> Unit
) {
    // Dados temporarios para demonstrar a interface
    val partidas = listOf(
        PartidaHistorico("05/11/2026", 3, 3),
        PartidaHistorico("04/11/2026", 2, 3),
        PartidaHistorico("03/11/2026", 1, 3),
        PartidaHistorico("02/11/2026", 3, 3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Historico de Partidas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Confira seus resultados anteriores!",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(partidas) { partida ->

                val porcentagem = if (partida.totalPerguntas > 0) {
                    (partida.acertos * 100) / partida.totalPerguntas
                } else {
                    0
                }

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Partida - ${partida.data}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Acertos: ${partida.acertos} de ${partida.totalPerguntas}"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        LinearProgressIndicator(
                            progress = { porcentagem / 100f },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "$porcentagem% de aproveitamento",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = voltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
