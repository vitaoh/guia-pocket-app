package com.example.guia_pocket_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.databinding.ActivityMainBinding
import com.example.guia_pocket_app.model.Muscle
import com.example.guia_pocket_app.ui.adapter.MuscleAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val muscleList = listOf(
        Muscle("Peito", R.drawable.chest),
        Muscle("Costas", R.drawable.back),
        Muscle("Pernas", R.drawable.legs),
        Muscle("Ombros", R.drawable.shoulders),
        Muscle("Braços", R.drawable.arms),
        Muscle("Abdômen", R.drawable.abs),
        Muscle("Panturrilha", R.drawable.calves),
        Muscle("Trapézio", R.drawable.traps)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window, true)

        // INFLOAR corretamente o layout com View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar ListView com adapter
        val adapter = MuscleAdapter(this, muscleList)
        binding.muscleListView.adapter = adapter

        binding.muscleListView.setOnItemClickListener { _, _, position, _ ->
            val muscleName = muscleList[position].name
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("muscle_name", muscleName)
            startActivity(intent)
        }

        // Alternar Tema Claro/Escuro
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

        // Alternar Idioma (Português ↔ Inglês)
        binding.btnChangeLanguage.setOnClickListener {
            val atual = AppCompatDelegate.getApplicationLocales()
            val isEnglish = !atual.isEmpty && atual[0]?.language == "en"
            val nextLang = if (isEnglish) "pt" else "en"
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(nextLang)
            )
        }
    }
}
