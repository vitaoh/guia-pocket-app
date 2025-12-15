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

class EditExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroExerciseBinding
    private lateinit var database: AppDatabase
    private var selectedImageUri: Uri? = null
    private var currentExercise: Exercise? = null
    private var exerciseId: Int = -1

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

        // Obter ID do exercício
        exerciseId = intent.getIntExtra("exercise_id", -1)

        if (exerciseId == -1) {
            Toast.makeText(this, "Exercício não encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.edit_exercise)

        database = AppDatabase.getInstance(this)

        // Carregar exercício do banco de dados
        loadExercise()
    }

    private fun loadExercise() {
        lifecycleScope.launch {
            try {
                currentExercise = database.exerciseDao().getExerciseById(exerciseId)

                if (currentExercise == null) {
                    Toast.makeText(
                        this@EditExerciseActivity,
                        "Exercício não encontrado",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    return@launch
                }

                setupUI()
                loadExerciseData()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@EditExerciseActivity,
                    "Erro ao carregar exercício",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun setupUI() {
        setupDifficultySpinner()
        setupEquipmentSpinner()

        binding.btnSelectImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnSaveExercise.setOnClickListener {
            updateExercise()
        }

        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun loadExerciseData() {
        currentExercise?.let { exercise ->
            // Preencher campos com dados do exercício
            binding.etExerciseName.setText(exercise.name)
            binding.etExerciseDescription.setText(exercise.description)
            binding.spinnerDifficulty.setText(exercise.difficulty, false)
            binding.spinnerEquipment.setText(exercise.equipment, false)
            binding.etYoutubeTerm.setText(exercise.youtubeSearchTerm)

            // Carregar imagem se existir
            if (exercise.imageUri.isNotEmpty()) {
                try {
                    // Verificar se a URI é válida antes de tentar carregar
                    val uriString = exercise.imageUri
                    if (uriString.startsWith("content://") || uriString.startsWith("file://")) {
                        val uri = Uri.parse(uriString)
                        binding.ivExerciseImage.setImageURI(uri)
                        selectedImageUri = uri
                        binding.btnSelectImage.text = getString(R.string.image_selected)
                    }
                } catch (e: Exception) {
                    // Se não conseguir carregar, não faz nada
                    e.printStackTrace()
                }
            }
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

        (binding.spinnerDifficulty as? AutoCompleteTextView)?.apply {
            setAdapter(adapter)
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
        }
    }

    private fun updateExercise() {
        val name = binding.etExerciseName.text.toString().trim()
        val description = binding.etExerciseDescription.text.toString().trim()
        val difficulty = binding.spinnerDifficulty.text.toString()
        val equipment = binding.spinnerEquipment.text.toString()
        val youtubeSearchTerm = binding.etYoutubeTerm.text.toString().trim()

        // Validações
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

        currentExercise?.let { originalExercise ->
            val updatedExercise = originalExercise.copy(
                name = name,
                description = description,
                difficulty = difficulty,
                equipment = equipment,
                youtubeSearchTerm = youtubeSearchTerm,
                imageUri = selectedImageUri?.toString() ?: originalExercise.imageUri
            )

            lifecycleScope.launch {
                try {
                    database.exerciseDao().updateExercise(updatedExercise)
                    Toast.makeText(
                        this@EditExerciseActivity,
                        R.string.exercise_updated_success,
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(Activity.RESULT_OK)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(
                        this@EditExerciseActivity,
                        "${getString(R.string.error_update_exercise)}: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}