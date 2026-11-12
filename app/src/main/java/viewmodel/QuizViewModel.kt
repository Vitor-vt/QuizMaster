
package com.example.quizmaster.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizmaster.data.local.Pergunta
import com.example.quizmaster.data.local.PerguntaDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class QuizUiState(
    val carregando: Boolean = true,
    val perguntas: List<Pergunta> = emptyList(),
    val perguntaAtual: Int = 0,
    val alternativaSelecionada: Int = -1,
    val pontuacao: Int = 0,
    val finalizado: Boolean = false
)

class QuizViewModel(
    private val dao: PerguntaDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        carregarPerguntas()
    }

    private fun carregarPerguntas() {
        viewModelScope.launch {
            dao.listarPerguntas().collect { todas ->
                val validas = todas.filter {
                    it.respostaCorreta in 0..3 &&
                            it.alternativaA.isNotBlank() &&
                            it.alternativaB.isNotBlank() &&
                            it.alternativaC.isNotBlank() &&
                            it.alternativaD.isNotBlank()
                }

                // Congela as perguntas durante uma partida.
                if (_uiState.value.carregando) {
                    _uiState.value = QuizUiState(
                        carregando = false,
                        perguntas = validas
                    )
                }
            }
        }
    }

    fun selecionarAlternativa(indice: Int) {
        val estado = _uiState.value

        if (!estado.finalizado &&
            estado.perguntas.isNotEmpty() &&
            indice in 0..3
        ) {
            _uiState.value = estado.copy(
                alternativaSelecionada = indice
            )
        }
    }

    fun proximaPergunta() {
        val estado = _uiState.value

        if (estado.finalizado ||
            estado.alternativaSelecionada == -1 ||
            estado.perguntas.isEmpty()
        ) return

        val pergunta = estado.perguntas[estado.perguntaAtual]

        val novaPontuacao = estado.pontuacao +
                if (estado.alternativaSelecionada ==
                    pergunta.respostaCorreta
                ) 1 else 0

        val ultima =
            estado.perguntaAtual == estado.perguntas.lastIndex

        _uiState.value = estado.copy(
            pontuacao = novaPontuacao,
            perguntaAtual = if (ultima)
                estado.perguntaAtual
            else
                estado.perguntaAtual + 1,
            alternativaSelecionada = -1,
            finalizado = ultima
        )
    }

    fun reiniciarQuiz() {
        val estado = _uiState.value

        _uiState.value = estado.copy(
            perguntaAtual = 0,
            alternativaSelecionada = -1,
            pontuacao = 0,
            finalizado = false
        )
    }
}
