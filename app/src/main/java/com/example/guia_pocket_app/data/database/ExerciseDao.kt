package com.example.guia_pocket_app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.guia_pocket_app.data.model.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Insert
    suspend fun insertExercise(exercise: Exercise): Long

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    // Buscar todos os exercícios de um músculo
    @Query("SELECT * FROM exercises WHERE muscleKey = :muscleKey ORDER BY name ASC")
    fun getExercisesByMuscleName(muscleKey: String): Flow<List<Exercise>>

    // Buscar exercício por ID
    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getExerciseById(id: Int): Exercise?

    // Buscar todos os exercícios
    @Query("SELECT * FROM exercises ORDER BY name ASC")
    fun getAllExercises(): Flow<List<Exercise>>

    // Buscar exercícios por nome (busca)
    @Query("SELECT * FROM exercises WHERE muscleKey = :muscleKey AND name LIKE '%' || :searchTerm || '%' ORDER BY name ASC")
    fun searchExercisesByName(muscleKey: String, searchTerm: String): Flow<List<Exercise>>

    // Deletar todos os exercícios de um músculo
    @Query("DELETE FROM exercises WHERE muscleKey = :muscleKey")
    suspend fun deleteExercisesByMuscle(muscleKey: String)
}