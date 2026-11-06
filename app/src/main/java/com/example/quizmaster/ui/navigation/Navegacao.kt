
package com.example.quizmaster.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quizmaster.ui.screens.TelaAdmin
import com.example.quizmaster.ui.screens.TelaGerenciarPerguntas
import com.example.quizmaster.ui.screens.TelaHistorico
import com.example.quizmaster.ui.screens.TelaInicial
import com.example.quizmaster.ui.screens.TelaMenu
import com.example.quizmaster.ui.screens.TelaQuiz
import com.example.quizmaster.ui.screens.TelaRanking
import com.example.quizmaster.ui.screens.TelaResultado

@Composable
fun Navegacao() {
    val navController = rememberNavController()

    val entradaAtual by navController.currentBackStackEntryAsState()
    val rotaAtual = entradaAtual?.destination?.route

    val rotasJogador = listOf(
        Rotas.MENU,
        Rotas.QUIZ,
        Rotas.RANKING,
        Rotas.HISTORICO
    )

    Scaffold(
        bottomBar = {
            if (rotaAtual in rotasJogador) {
                BarraNavegacao(
                    rotaAtual = rotaAtual,
                    navegar = { destino ->
                        navController.navigate(destino) {
                            popUpTo(Rotas.MENU) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingInterno ->

        NavHost(
            navController = navController,
            startDestination = Rotas.INICIO,
            modifier = Modifier.padding(paddingInterno)
        ) {

            composable(Rotas.INICIO) {
                TelaInicial(
                    abrirJogador = {
                        navController.navigate(Rotas.MENU)
                    },
                    abrirAdmin = {
                        navController.navigate(Rotas.ADMIN)
                    }
                )
            }

            composable(Rotas.MENU) {
                TelaMenu(
                    abrirQuiz = {
                        navController.navigate(Rotas.QUIZ)
                    },
                    abrirRanking = {
                        navController.navigate(Rotas.RANKING)
                    },
                    abrirHistorico = {
                        navController.navigate(Rotas.HISTORICO)
                    },
                    voltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Rotas.QUIZ) {
                TelaQuiz(
                    finalizarQuiz = { pontuacao, total ->
                        navController.navigate(
                            "${Rotas.RESULTADO}/$pontuacao/$total"
                        ) {
                            popUpTo(Rotas.QUIZ) {
                                inclusive = true
                            }
                        }
                    },
                    voltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Rotas.RANKING) {
                TelaRanking(
                    voltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Rotas.HISTORICO) {
                TelaHistorico(
                    voltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = "${Rotas.RESULTADO}/{pontuacao}/{total}"
            ) { entrada ->
                val pontuacao = entrada.arguments
                    ?.getString("pontuacao")
                    ?.toIntOrNull() ?: 0

                val total = entrada.arguments
                    ?.getString("total")
                    ?.toIntOrNull() ?: 0

                TelaResultado(
                    pontuacao = pontuacao,
                    totalPerguntas = total,
                    voltarMenu = {
                        navController.navigate(Rotas.MENU) {
                            popUpTo(Rotas.MENU) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Rotas.ADMIN) {
                TelaAdmin(
                    gerenciarPerguntas = {
                        navController.navigate(Rotas.GERENCIAR_PERGUNTAS)
                    },
                    voltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Rotas.GERENCIAR_PERGUNTAS) {
                TelaGerenciarPerguntas(
                    voltar = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
