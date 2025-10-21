package com.example.guia_pocket_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val name: String,
    val description: String,
    val muscles: List<String>,
    val difficulty: String,
    val equipment: String,
    val youtubeSearchTerm: String
) : Parcelable
