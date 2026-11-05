
package com.example.quizmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quizmaster.ui.screens.TelaInicial
import com.example.quizmaster.ui.theme.QuizMasterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizMasterTheme {
                TelaInicial(
                    abrirJogador = {
                        // Navegacao do jogador sera criada depois
                    },
                    abrirAdmin = {
                        // Navegacao do administrador sera criada depois
                    }
                )
            }
        }
    }
}
