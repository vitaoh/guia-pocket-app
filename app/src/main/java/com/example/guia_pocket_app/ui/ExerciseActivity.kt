package com.example.guia_pocket_app.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.guia_pocket_app.R
import com.google.android.material.appbar.MaterialToolbar

class ExerciseActivity : AppCompatActivity() {

    private val exercisesMap = mapOf(
        "Peito" to listOf("Supino reto", "Supino inclinado", "Crucifixo"),
        "Costas" to listOf("Barra fixa", "Remada", "Pulldown"),
        "Pernas" to listOf("Agachamento", "Leg press", "Cadeira extensora"),
        "Ombros" to listOf("Desenvolvimento", "Elevação lateral", "Encolhimento"),
        "Braços" to listOf("Rosca direta", "Tríceps pulley", "Martelo"),
        "Abdômen" to listOf("Prancha", "Abdominal", "Elevação de pernas"),
        "Panturrilha" to listOf("Gêmeos em pé", "Gêmeos sentado"),
        "Trapézio" to listOf("Encolhimento com barra", "Remada alta")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContentView(R.layout.activity_exercise)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val muscle = intent.getStringExtra("muscle_name") ?: "Músculo"
        val titleView = findViewById<TextView>(R.id.muscleTitle)
        val listView = findViewById<ListView>(R.id.exerciseListView)

        titleView.text = getString(R.string.exercise_list_title, muscle)

        val exercises = exercisesMap[muscle] ?: listOf(getString(R.string.no_exercises))
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exercises)
        listView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
