package com.example.guia_pocket_app.ui.activity

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.data.database.AppDatabase
import com.example.guia_pocket_app.data.model.Exercise
import com.example.guia_pocket_app.databinding.ActivityCadastroExerciseBinding
import kotlinx.coroutines.launch

class CadastroExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroExerciseBinding
    private lateinit var database: AppDatabase
    private var selectedImageUri: Uri? = null
    private lateinit var muscleKey: String

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            binding.ivExerciseImage.setImageURI(uri)
            binding.btnSelectImage.text = getString(R.string.image_selected)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityCadastroExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.add_exercise)

        muscleKey = intent.getStringExtra("muscle_key") ?: "chest"

        database = AppDatabase.getInstance(this)

        setupUI()
    }

    private fun setupUI() {
        setupDifficultySpinner()
        setupEquipmentSpinner()

        binding.btnSelectImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnSaveExercise.setOnClickListener {
            saveExercise()
        }

        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun setupDifficultySpinner() {
        val difficulties = listOf(
            getString(R.string.difficulty_beginner),
            getString(R.string.difficulty_intermediate),
            getString(R.string.difficulty_advanced)
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            difficulties
        )

        binding.spinnerDifficulty?.apply {
            setAdapter(adapter)
            setText(difficulties[0], false)
        }
    }

    private fun setupEquipmentSpinner() {
        val equipment = listOf(
            getString(R.string.equipment_barbell),
            getString(R.string.equipment_dumbbell),
            getString(R.string.equipment_machine),
            getString(R.string.equipment_cable),
            getString(R.string.equipment_resistance_band),
            getString(R.string.equipment_bodyweight)
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            equipment
        )

        (binding.spinnerEquipment as? AutoCompleteTextView)?.apply {
            setAdapter(adapter)
            setText(equipment[0], false)
        }
    }

    private fun saveExercise() {
        val name = binding.etExerciseName.text.toString().trim()
        val description = binding.etExerciseDescription.text.toString().trim()
        val difficulty = binding.spinnerDifficulty.text.toString()
        val equipment = binding.spinnerEquipment.text.toString()
        val youtubeSearchTerm = binding.etYoutubeTerm.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_name, Toast.LENGTH_SHORT).show()
            return
        }

        if (description.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_description, Toast.LENGTH_SHORT).show()
            return
        }

        if (youtubeSearchTerm.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_youtube, Toast.LENGTH_SHORT).show()
            return
        }

        val exercise = Exercise.create(
            name = name,
            description = description,
            muscles = listOf(getMuscleNameByKey(muscleKey)),
            difficulty = difficulty,
            equipment = equipment,
            youtubeSearchTerm = youtubeSearchTerm,
            imageUri = selectedImageUri?.toString() ?: "",
            muscleKey = muscleKey
        )

        lifecycleScope.launch {
            try {
                database.exerciseDao().insertExercise(exercise)
                Toast.makeText(
                    this@CadastroExerciseActivity,
                    R.string.exercise_saved_success,
                    Toast.LENGTH_SHORT
                ).show()
                setResult(Activity.RESULT_OK)
                finish()
            } catch (e: Exception) {
                Toast.makeText(
                    this@CadastroExerciseActivity,
                    "${getString(R.string.error_save_exercise)}: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}