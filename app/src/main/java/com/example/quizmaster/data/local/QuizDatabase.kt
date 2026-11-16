
package com.example.quizmaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Pergunta::class],
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun perguntaDao(): PerguntaDao
}
