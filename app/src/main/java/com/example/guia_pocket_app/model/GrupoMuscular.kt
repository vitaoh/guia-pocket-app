package com.example.guia_pocket_app.model

data class GrupoMuscular(
    val id: Int,
    val nome: String,
    val nomeEn: String,
    val cor: String,
    val icone: Int,
    val exercicios: List<Exercicio>
)
