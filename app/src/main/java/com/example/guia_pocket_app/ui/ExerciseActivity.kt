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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContentView(R.layout.activity_exercise)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        // Recebe a KEY do músculo
        val muscleKey = intent.getStringExtra("muscle_key") ?: "chest"
        val titleView = findViewById<TextView>(R.id.muscleTitle)
        val listView = findViewById<ListView>(R.id.exerciseListView)

        // Busca o nome localizado do músculo
        val muscleName = getMuscleNameByKey(muscleKey)
        titleView.text = getString(R.string.exercise_list_title, muscleName)

        // Busca os exercícios do array de recursos
        val exercises = getExercisesByKey(muscleKey)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exercises)
        listView.adapter = adapter
    }

    private fun getMuscleNameByKey(key: String): String {
        return when (key) {
            "chest" -> getString(R.string.muscle_chest)
            "back" -> getString(R.string.muscle_back)
            "legs" -> getString(R.string.muscle_legs)
            "shoulders" -> getString(R.string.muscle_shoulders)
            "arms" -> getString(R.string.muscle_arms)
            "abs" -> getString(R.string.muscle_abs)
            "calves" -> getString(R.string.muscle_calves)
            "traps" -> getString(R.string.muscle_traps)
            else -> getString(R.string.muscle_chest)
        }
    }

    private fun getExercisesByKey(key: String): Array<String> {
        val arrayResId = when (key) {
            "chest" -> R.array.exercises_chest
            "back" -> R.array.exercises_back
            "legs" -> R.array.exercises_legs
            "shoulders" -> R.array.exercises_shoulders
            "arms" -> R.array.exercises_arms
            "abs" -> R.array.exercises_abs
            "calves" -> R.array.exercises_calves
            "traps" -> R.array.exercises_traps
            else -> R.array.exercises_chest
        }

        return resources.getStringArray(arrayResId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
