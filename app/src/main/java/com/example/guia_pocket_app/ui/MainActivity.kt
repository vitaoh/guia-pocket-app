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
