package com.example.guia_pocket_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity(tableName = "exercises")
@Parcelize
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val muscles: String,
    val difficulty: String,
    val equipment: String,
    val youtubeSearchTerm: String,
    val imageUri: String = "",
    val muscleKey: String = ""
) : Parcelable {

    fun getMusclesList(): List<String> {
        return if (muscles.isEmpty()) emptyList()
        else muscles.split(",").map { it.trim() }
    }

    companion object {
        fun create(
            id: Int = 0,
            name: String,
            description: String,
            muscles: List<String>,
            difficulty: String,
            equipment: String,
            youtubeSearchTerm: String,
            imageUri: String = "",
            muscleKey: String = ""
        ) = Exercise(
            id = id,
            name = name,
            description = description,
            muscles = muscles.joinToString(", "),
            difficulty = difficulty,
            equipment = equipment,
            youtubeSearchTerm = youtubeSearchTerm,
            imageUri = imageUri,
            muscleKey = muscleKey
        )
    }
}