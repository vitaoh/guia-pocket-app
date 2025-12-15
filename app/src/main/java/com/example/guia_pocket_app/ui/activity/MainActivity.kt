package com.example.guia_pocket_app.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.WindowCompat
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.databinding.ActivityMainBinding
import com.example.guia_pocket_app.data.model.Muscle
import com.example.guia_pocket_app.ui.adapter.MuscleAdapter
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val muscleList by lazy {
        listOf(
            Muscle(getString(R.string.muscle_chest), R.drawable.chest, "chest"),
            Muscle(getString(R.string.muscle_back), R.drawable.back, "back"),
            Muscle(getString(R.string.muscle_legs), R.drawable.legs, "legs"),
            Muscle(getString(R.string.muscle_shoulders), R.drawable.shoulders, "shoulders"),
            Muscle(getString(R.string.muscle_arms), R.drawable.arms, "arms"),
            Muscle(getString(R.string.muscle_abs), R.drawable.abs, "abs"),
            Muscle(getString(R.string.muscle_calves), R.drawable.calves, "calves"),
            Muscle(getString(R.string.muscle_traps), R.drawable.traps, "traps")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.muscleRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = MuscleAdapter(muscleList) { position ->
            val muscle = muscleList[position]
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("muscle_key", muscle.key)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        binding.btnToggleTheme.setOnClickListener {
            val isNight = AppCompatDelegate.getDefaultNightMode() ==
                    AppCompatDelegate.MODE_NIGHT_YES
            val mode = if (isNight) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }

        binding.btnChangeLanguage.setOnClickListener {
            val atual = AppCompatDelegate.getApplicationLocales()
            val isEnglish = !atual.isEmpty && atual[0]?.language == "en"
            val nextLang = if (isEnglish) "pt" else "en"
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(nextLang)
            )
            recreate()
        }
    }
}