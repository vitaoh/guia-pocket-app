package com.example.guia_pocket_app.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.data.database.AppDatabase
import com.example.guia_pocket_app.data.model.Exercise
import com.example.guia_pocket_app.databinding.ActivityExerciseBinding
import com.example.guia_pocket_app.ui.adapter.ExerciseAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private lateinit var database: AppDatabase
    private lateinit var muscleKey: String
    private lateinit var adapter: ExerciseAdapter

    // StateFlow para gerenciar o termo de busca
    private val searchQuery = MutableStateFlow("")

    private val cadastroLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // A lista será atualizada automaticamente pelo Flow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        muscleKey = intent.getStringExtra("muscle_key") ?: "chest"
        database = AppDatabase.getInstance(this)

        val muscleName = getMuscleNameByKey(muscleKey)
        binding.muscleTitle.text = getString(R.string.exercise_list_title, muscleName)

        setupRecyclerView()
        setupSearchFilter()
        setupAddButton()
        observeExercises()
    }

    private fun setupRecyclerView() {
        adapter = ExerciseAdapter(emptyList()) { exercise ->
            val intent = Intent(this, ExerciseDetailActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivity(intent)
        }

        binding.exerciseRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ExerciseActivity)
            adapter = this@ExerciseActivity.adapter
        }
    }

    private fun setupSearchFilter() {
        binding.etSearchExercise.doOnTextChanged { text, _, _, _ ->
            searchQuery.value = text?.toString() ?: ""
        }
    }

    private fun setupAddButton() {
        binding.fabAddExercise.setOnClickListener {
            val intent = Intent(this, CadastroExerciseActivity::class.java)
            intent.putExtra("muscle_key", muscleKey)
            cadastroLauncher.launch(intent)
        }
    }

    private fun observeExercises() {
        lifecycleScope.launch {
            searchQuery
                .debounce(300) // Aguarda 300ms após última digitação
                .flatMapLatest { query ->
                    if (query.isEmpty()) {
                        database.exerciseDao().getExercisesByMuscleName(muscleKey)
                    } else {
                        database.exerciseDao().searchExercisesByName(muscleKey, query)
                    }
                }
                .catch { e ->
                    e.printStackTrace()
                    emit(emptyList())
                }
                .collect { exercises ->
                    updateAdapter(exercises)
                }
        }
    }

    private fun updateAdapter(exercises: List<Exercise>) {
        adapter = ExerciseAdapter(exercises) { exercise ->
            val intent = Intent(this, ExerciseDetailActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivity(intent)
        }
        binding.exerciseRecyclerView.adapter = adapter
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