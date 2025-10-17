package com.example.guia_pocket_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.model.Muscle
import com.example.guia_pocket_app.ui.adapter.MuscleAdapter

class MainActivity : AppCompatActivity() {

    private val muscleList = listOf(
        Muscle("Peito", R.drawable.ic_chest),
        Muscle("Costas", R.drawable.ic_back),
        Muscle("Pernas", R.drawable.ic_legs),
        Muscle("Ombros", R.drawable.ic_shoulders),
        Muscle("Braços", R.drawable.ic_arms),
        Muscle("Abdômen", R.drawable.ic_abs),
        Muscle("Panturrilha", R.drawable.ic_calves),
        Muscle("Trapézio", R.drawable.ic_traps)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.muscleListView)
        val adapter = MuscleAdapter(this, muscleList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val muscleName = muscleList[position].name
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("muscle_name", muscleName)
            startActivity(intent)
        }
    }
}
