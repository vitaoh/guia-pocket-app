package com.example.guia_pocket_app.model

data class Exercicio(
    val id: Int,
    val nome: String,
    val nomeEn: String,
    val grupoMuscular: String,
    val instrucoes: String,
    val instrucoesEn: String,
    val imagem: Int,
    val series: Int,
    val repeticoes: String,
    val tempoDescanso: Int,
    val nivel: String,
    val links: ExercicioLinks
)

data class ExercicioLinks(
    val youtubeUrl: String,
    val whatsappText: String,
    val personalPhone: String,
    val academiaLocation: String
)
