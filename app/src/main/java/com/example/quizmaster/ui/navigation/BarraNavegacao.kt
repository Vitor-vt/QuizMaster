
package com.example.quizmaster.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BarraNavegacao(
    rotaAtual: String?,
    navegar: (String) -> Unit
) {
    NavigationBar {

        NavigationBarItem(
            selected = rotaAtual == Rotas.MENU,
            onClick = { navegar(Rotas.MENU) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Menu"
                )
            },
            label = { Text("Menu") }
        )

        NavigationBarItem(
            selected = rotaAtual == Rotas.RANKING,
            onClick = { navegar(Rotas.RANKING) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Ranking"
                )
            },
            label = { Text("Ranking") }
        )

        NavigationBarItem(
            selected = rotaAtual == Rotas.HISTORICO,
            onClick = { navegar(Rotas.HISTORICO) },
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Historico"
                )
            },
            label = { Text("Historico") }
        )
    }
}
