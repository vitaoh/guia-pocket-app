package com.example.guia_pocket_app.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.data.model.Exercise
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton

class ExerciseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContentView(R.layout.activity_exercise_detail)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val exercise = intent.getParcelableExtra<Exercise>("exercise")

        exercise?.let {
            findViewById<TextView>(R.id.exerciseName).text = it.name
            findViewById<TextView>(R.id.exerciseDescription).text = it.description
            findViewById<TextView>(R.id.exerciseMuscles).text = it.muscles
            findViewById<TextView>(R.id.exerciseDifficulty).text = it.difficulty
            findViewById<TextView>(R.id.exerciseEquipment).text = it.equipment


            findViewById<MaterialButton>(R.id.btnWatchYoutube).setOnClickListener { _ ->
                openYouTubeSearch(it.youtubeSearchTerm)
            }
        }
    }

    private fun openYouTubeSearch(searchTerm: String) {
        val searchQuery = "$searchTerm tutorial"
        val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=${Uri.encode(searchQuery)}"))
        startActivity(youtubeIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
