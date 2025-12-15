package com.example.guia_pocket_app.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.data.database.AppDatabase
import com.example.guia_pocket_app.data.model.Exercise
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class ExerciseDetailActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var currentExercise: Exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContentView(R.layout.activity_exercise_detail)

        database = AppDatabase.getInstance(this)
        currentExercise = intent.getParcelableExtra("exercise")!!

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        // Adicionar menu na toolbar
        toolbar.inflateMenu(R.menu.menu_exercise_detail)
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_edit -> {
                    openEditExercise()
                    true
                }
                R.id.menu_delete -> {
                    showDeleteConfirmation()
                    true
                }
                else -> false
            }
        }

        updateUI()
    }

    private fun updateUI() {
        findViewById<TextView>(R.id.exerciseName).text = currentExercise.name
        findViewById<TextView>(R.id.exerciseDescription).text = currentExercise.description
        findViewById<TextView>(R.id.exerciseMuscles).text = currentExercise.muscles
        findViewById<TextView>(R.id.exerciseDifficulty).text = currentExercise.difficulty
        findViewById<TextView>(R.id.exerciseEquipment).text = currentExercise.equipment

        findViewById<MaterialButton>(R.id.btnWatchYoutube).setOnClickListener {
            openYouTubeSearch(currentExercise.youtubeSearchTerm)
        }
    }

    private fun openEditExercise() {
        try {
            val intent = Intent(this, EditExerciseActivity::class.java)
            intent.putExtra("exercise_id", currentExercise.id)  // Passar apenas o ID
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erro ao abrir edição", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDeleteConfirmation() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirm_delete))
            .setMessage(getString(R.string.delete_exercise_message, currentExercise.name))
            .setPositiveButton(R.string.delete) { _, _ ->
                deleteExercise()
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun deleteExercise() {
        lifecycleScope.launch {
            try {
                database.exerciseDao().deleteExercise(currentExercise)
                Toast.makeText(
                    this@ExerciseDetailActivity,
                    getString(R.string.exercise_deleted_success),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(
                    this@ExerciseDetailActivity,
                    getString(R.string.error_delete_exercise),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openYouTubeSearch(searchTerm: String) {
        val searchQuery = "$searchTerm tutorial"
        val youtubeIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/results?search_query=${Uri.encode(searchQuery)}")
        )
        startActivity(youtubeIntent)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            database.exerciseDao().getExerciseById(currentExercise.id)?.let { updatedExercise ->
                currentExercise = updatedExercise
                updateUI()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}