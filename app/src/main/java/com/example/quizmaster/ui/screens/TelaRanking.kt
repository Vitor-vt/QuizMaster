
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class JogadorRanking(
    val nome: String,
    val pontos: Int
)

@Composable
fun TelaRanking(
    voltar: () -> Unit
) {
    // Dados temporarios para testar a interface
    val jogadores = listOf(
        JogadorRanking("Ana", 950),
        JogadorRanking("Carlos", 780),
        JogadorRanking("Pedro", 620),
        JogadorRanking("Maria", 890),
        JogadorRanking("Joao", 450)
    )

    // Ordena os jogadores pela maior pontuacao
    val rankingOrdenado = jogadores.sortedByDescending {
        it.pontos
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Ranking de Jogadores",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Confira os melhores jogadores!",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(rankingOrdenado) { indice, jogador ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "${indice + 1}º Lugar",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = jogador.nome,
                                fontSize = 16.sp
                            )
                        }

                        Text(
                            text = "${jogador.pontos} pts",
                            fontWeight = FontWeight.Bold,
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
