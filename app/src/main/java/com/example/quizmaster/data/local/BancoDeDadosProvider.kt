
package com.example.quizmaster.data.local

import android.content.Context
import androidx.room.Room

object BancoDeDadosProvider {

    @Volatile
    private var instancia: QuizDatabase? = null

    fun obterBanco(context: Context): QuizDatabase {
        return instancia ?: synchronized(this) {
            instancia ?: Room.databaseBuilder(
                context.applicationContext,
                QuizDatabase::class.java,
                "quizmaster_database"
            ).build().also {
                instancia = it
            }
        }
    }
}
