
package com.example.quizmaster.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PerguntaDao {

    @Query("SELECT * FROM perguntas ORDER BY id ASC")
    fun listarPerguntas(): Flow<List<Pergunta>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirPergunta(pergunta: Pergunta)

    @Update
    suspend fun atualizarPergunta(pergunta: Pergunta)

    @Delete
    suspend fun excluirPergunta(pergunta: Pergunta)
}
