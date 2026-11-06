
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaQuiz(
    finalizarQuiz: (Int, Int) -> Unit,
    voltar: () -> Unit
) {
    val perguntas = listOf(
        "Qual é a capital do Brasil?",
        "Quanto é 5 + 5?",
        "Qual planeta é conhecido como planeta vermelho?"
    )

    val alternativas = listOf(
        listOf("São Paulo", "Brasília", "Rio de Janeiro", "Salvador"),
        listOf("8", "9", "10", "12"),
        listOf("Terra", "Vênus", "Marte", "Júpiter")
    )

    val respostasCorretas = listOf(1, 2, 2)

    var perguntaAtual by remember { mutableIntStateOf(0) }
    var alternativaSelecionada by remember { mutableIntStateOf(-1) }
    var pontuacao by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "QuizMaster",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Pergunta ${perguntaAtual + 1} de ${perguntas.size}",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = {
                (perguntaAtual + 1).toFloat() / perguntas.size
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = perguntas[perguntaAtual],
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        alternativas[perguntaAtual].forEachIndexed { indice, alternativa ->
            OutlinedButton(
                onClick = {
                    alternativaSelecionada = indice
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (alternativaSelecionada == indice) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Text(alternativa)
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val novaPontuacao =
                    pontuacao + if (
                        alternativaSelecionada ==
                        respostasCorretas[perguntaAtual]
                    ) 1 else 0

                pontuacao = novaPontuacao

                if (perguntaAtual < perguntas.lastIndex) {
                    perguntaAtual++
                    alternativaSelecionada = -1
                } else {
                    finalizarQuiz(
                        novaPontuacao,
                        perguntas.size
                    )
                }
            },
            enabled = alternativaSelecionada != -1,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (perguntaAtual == perguntas.lastIndex) {
                    "Finalizar Quiz"
                } else {
                    "Próxima Pergunta"
                }
            )
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
