
package com.example.quizmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizmaster.data.local.BancoDeDados
import com.example.quizmaster.data.local.Pergunta
import kotlinx.coroutines.launch

@Composable
fun TelaGerenciarPerguntas(voltar: () -> Unit) {

    val context = LocalContext.current
    val banco = remember(context) {
        BancoDeDados.getDatabase(context)
    }
    val dao = banco.perguntaDao()
    val perguntas by dao.listarPerguntas()
        .collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()

    var mostrarCadastro by remember { mutableStateOf(false) }
    var texto by remember { mutableStateOf("") }
    var a by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }
    var c by remember { mutableStateOf("") }
    var d by remember { mutableStateOf("") }
    var correta by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Gerenciar Perguntas",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { mostrarCadastro = !mostrarCadastro },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Pergunta")
        }

        if (mostrarCadastro) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = { Text("Pergunta") },
                    modifier = Modifier.fillMaxWidth()
                )

                listOf(a, b, c, d).forEachIndexed { indice, valor ->
                    OutlinedTextField(
                        value = valor,
                        onValueChange = { novo ->
                            when (indice) {
                                0 -> a = novo
                                1 -> b = novo
                                2 -> c = novo
                                3 -> d = novo
                            }
                        },
                        label = {
                            Text("Alternativa ${'A' + indice}")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text("Selecione a resposta correta:")

                listOf("A", "B", "C", "D")
                    .forEachIndexed { indice, letra ->
                        Row {
                            RadioButton(
                                selected = correta == indice,
                                onClick = { correta = indice }
                            )
                            Text(
                                letra,
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                    }

                Button(
                    onClick = {
                        if (
                            texto.isNotBlank() &&
                            a.isNotBlank() &&
                            b.isNotBlank() &&
                            c.isNotBlank() &&
                            d.isNotBlank() &&
                            correta in 0..3
                        ) {
                            scope.launch {
                                dao.inserir(
                                    Pergunta(
                                        texto = texto.trim(),
                                        alternativaA = a.trim(),
                                        alternativaB = b.trim(),
                                        alternativaC = c.trim(),
                                        alternativaD = d.trim(),
                                        respostaCorreta = correta
                                    )
                                )
                                texto = ""
                                a = ""
                                b = ""
                                c = ""
                                d = ""
                                correta = -1
                                mostrarCadastro = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Pergunta")
                }
            }
        } else {
            Spacer(Modifier.height(16.dp))

            Text(
                "Perguntas cadastradas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(perguntas, key = { it.id }) { pergunta ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(pergunta.texto)

                            if (pergunta.respostaCorreta == -1) {
                                Text(
                                    "Sem alternativas cadastradas",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }

                            TextButton(
                                onClick = {
                                    scope.launch {
                                        dao.excluir(pergunta)
                                    }
                                }
                            ) {
                                Text("Excluir")
                            }
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
