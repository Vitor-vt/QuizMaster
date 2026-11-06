
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaGerenciarPerguntas(
    voltar: () -> Unit
) {
    var mostrarCadastro by remember { mutableStateOf(false) }
    var textoPergunta by remember { mutableStateOf("") }

    val perguntas = remember {
        mutableStateListOf(
            "Qual é a capital do Brasil?",
            "Quanto é 5 + 5?",
            "Qual planeta é conhecido como planeta vermelho?"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gerenciar Perguntas",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { mostrarCadastro = !mostrarCadastro },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Pergunta")
        }

        if (mostrarCadastro) {
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = textoPergunta,
                onValueChange = { textoPergunta = it },
                label = { Text("Digite a pergunta") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (textoPergunta.isNotBlank()) {
                        perguntas.add(textoPergunta.trim())
                        textoPergunta = ""
                        mostrarCadastro = false
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Pergunta")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Perguntas cadastradas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(perguntas.toList()) { pergunta ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = pergunta)

                        Spacer(modifier = Modifier.height(8.dp))

                        TextButton(
                            onClick = {
                                perguntas.remove(pergunta)
                            }
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        OutlinedButton(
            onClick = voltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
