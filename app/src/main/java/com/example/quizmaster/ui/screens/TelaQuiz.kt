
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizmaster.data.local.BancoDeDados
import com.example.quizmaster.ui.viewmodel.QuizViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TelaQuiz(
    finalizarQuiz: (Int, Int) -> Unit,
    voltar: () -> Unit
) {
    val context = LocalContext.current.applicationContext

    val banco = remember(context) {
        BancoDeDados.getDatabase(context)
    }



    val viewModel: QuizViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                return QuizViewModel(banco.perguntaDao()) as T
            }
        }
    )


    val estado by viewModel.uiState.collectAsState()

    var resultadoEnviado by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(estado.finalizado) {
        if (estado.finalizado && !resultadoEnviado) {
            resultadoEnviado = true
            finalizarQuiz(
                estado.pontuacao,
                estado.perguntas.size
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        if (estado.carregando) {
            CircularProgressIndicator()
        } else if (estado.perguntas.isEmpty()) {
            Text(
                text = "Nenhuma pergunta com alternativas cadastradas."
            )
        } else {
            val pergunta = estado.perguntas[estado.perguntaAtual]

            Text(
                text = "Pergunta ${estado.perguntaAtual + 1} " +
                        "de ${estado.perguntas.size}",
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = {
                    (estado.perguntaAtual + 1).toFloat() /
                            estado.perguntas.size
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = pergunta.texto,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            val alternativas = listOf(
                pergunta.alternativaA,
                pergunta.alternativaB,
                pergunta.alternativaC,
                pergunta.alternativaD
            )

            alternativas.forEachIndexed { indice, alternativa ->
                OutlinedButton(
                    onClick = {
                        viewModel.selecionarAlternativa(indice)
                    },
                    enabled = !estado.finalizado,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor =
                            if (estado.alternativaSelecionada == indice) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.surface
                            }
                    )
                ) {
                    Text("${'A' + indice}) $alternativa")
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.proximaPergunta()
                },
                enabled = estado.alternativaSelecionada != -1 &&
                        !estado.finalizado,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (estado.perguntaAtual ==
                        estado.perguntas.lastIndex
                    ) {
                        "Finalizar Quiz"
                    } else {
                        "Próxima Pergunta"
                    }
                )
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
