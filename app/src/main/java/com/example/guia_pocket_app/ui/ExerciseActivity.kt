package com.example.guia_pocket_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.model.Exercise
import com.google.android.material.appbar.MaterialToolbar

class ExerciseActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exercises: List<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContentView(R.layout.activity_exercise)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val muscleKey = intent.getStringExtra("muscle_key") ?: "chest"
        val titleView = findViewById<TextView>(R.id.muscleTitle)
        recyclerView = findViewById(R.id.exerciseRecyclerView)

        val muscleName = getMuscleNameByKey(muscleKey)
        titleView.text = getString(R.string.exercise_list_title, muscleName)

        exercises = getExercisesByKey(muscleKey)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ExerciseAdapter(exercises) { exercise ->
            val intent = Intent(this, ExerciseDetailActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivity(intent)
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

    private fun getExercisesByKey(key: String): List<Exercise> {
        return when (key) {
            "chest" -> getChestExercises()
            "back" -> getBackExercises()
            "legs" -> getLegExercises()
            "shoulders" -> getShoulderExercises()
            "arms" -> getArmExercises()
            "abs" -> getAbsExercises()
            "calves" -> getCalvesExercises()
            "traps" -> getTrapsExercises()
            else -> getChestExercises()
        }
    }

    private fun getChestExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_chest_1),
                description = getString(R.string.exercise_chest_1_desc),
                muscles = listOf(
                    getString(R.string.muscle_chest),
                    getString(R.string.muscle_shoulders),
                    getString(R.string.muscle_arms)
                ),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "flat bench press"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_2),
                description = getString(R.string.exercise_chest_2_desc),
                muscles = listOf(
                    getString(R.string.muscle_chest),
                    getString(R.string.muscle_shoulders)
                ),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "incline bench press"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_3),
                description = getString(R.string.exercise_chest_3_desc),
                muscles = listOf(getString(R.string.muscle_chest)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "decline bench press"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_4),
                description = getString(R.string.exercise_chest_4_desc),
                muscles = listOf(getString(R.string.muscle_chest)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "dumbbell fly"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_5),
                description = getString(R.string.exercise_chest_5_desc),
                muscles = listOf(getString(R.string.muscle_chest)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "incline dumbbell fly"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_6),
                description = getString(R.string.exercise_chest_6_desc),
                muscles = listOf(getString(R.string.muscle_chest)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_cable),
                youtubeSearchTerm = "cable crossover"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_7),
                description = getString(R.string.exercise_chest_7_desc),
                muscles = listOf(
                    getString(R.string.muscle_chest),
                    getString(R.string.muscle_shoulders),
                    getString(R.string.muscle_arms)
                ),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "push up"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_8),
                description = getString(R.string.exercise_chest_8_desc),
                muscles = listOf(getString(R.string.muscle_chest), getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "chest dip"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_9),
                description = getString(R.string.exercise_chest_9_desc),
                muscles = listOf(getString(R.string.muscle_chest), getString(R.string.muscle_back)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "dumbbell pullover"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_10),
                description = getString(R.string.exercise_chest_10_desc),
                muscles = listOf(
                    getString(R.string.muscle_chest),
                    getString(R.string.muscle_shoulders),
                    getString(R.string.muscle_arms)
                ),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "dumbbell bench press"
            ),
            Exercise(
                name = getString(R.string.exercise_chest_11),
                description = getString(R.string.exercise_chest_11_desc),
                muscles = listOf(getString(R.string.muscle_chest)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "pec deck"
            )
        )
    }

    private fun getBackExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_back_1),
                description = getString(R.string.exercise_back_1_desc),
                muscles = listOf(getString(R.string.muscle_back), getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "pull up"
            ),
            Exercise(
                name = getString(R.string.exercise_back_2),
                description = getString(R.string.exercise_back_2_desc),
                muscles = listOf(getString(R.string.muscle_back), getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "chin up"
            ),
            Exercise(
                name = getString(R.string.exercise_back_3),
                description = getString(R.string.exercise_back_3_desc),
                muscles = listOf(getString(R.string.muscle_back), getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "bent over row"
            ),
            Exercise(
                name = getString(R.string.exercise_back_4),
                description = getString(R.string.exercise_back_4_desc),
                muscles = listOf(getString(R.string.muscle_back)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "t bar row"
            ),
            Exercise(
                name = getString(R.string.exercise_back_5),
                description = getString(R.string.exercise_back_5_desc),
                muscles = listOf(getString(R.string.muscle_back)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "one arm dumbbell row"
            ),
            Exercise(
                name = getString(R.string.exercise_back_6),
                description = getString(R.string.exercise_back_6_desc),
                muscles = listOf(getString(R.string.muscle_back), getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "lat pulldown"
            ),
            Exercise(
                name = getString(R.string.exercise_back_7),
                description = getString(R.string.exercise_back_7_desc),
                muscles = listOf(getString(R.string.muscle_back), getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_cable),
                youtubeSearchTerm = "cable row"
            ),
            Exercise(
                name = getString(R.string.exercise_back_8),
                description = getString(R.string.exercise_back_8_desc),
                muscles = listOf(
                    getString(R.string.muscle_back),
                    getString(R.string.muscle_shoulders)
                ),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "upright row"
            ),
            Exercise(
                name = getString(R.string.exercise_back_9),
                description = getString(R.string.exercise_back_9_desc),
                muscles = listOf(getString(R.string.muscle_back), getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_advanced),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "deadlift"
            ),
            Exercise(
                name = getString(R.string.exercise_back_10),
                description = getString(R.string.exercise_back_10_desc),
                muscles = listOf(getString(R.string.muscle_back)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "seated row"
            ),
            Exercise(
                name = getString(R.string.exercise_back_11),
                description = getString(R.string.exercise_back_11_desc),
                muscles = listOf(getString(R.string.muscle_back)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "inverted row"
            )
        )
    }

    private fun getLegExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_legs_1),
                description = getString(R.string.exercise_legs_1_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "squat"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_2),
                description = getString(R.string.exercise_legs_2_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "front squat"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_3),
                description = getString(R.string.exercise_legs_3_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "leg press"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_4),
                description = getString(R.string.exercise_legs_4_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "leg extension"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_5),
                description = getString(R.string.exercise_legs_5_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "leg curl"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_6),
                description = getString(R.string.exercise_legs_6_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "lunge"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_7),
                description = getString(R.string.exercise_legs_7_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "bulgarian split squat"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_8),
                description = getString(R.string.exercise_legs_8_desc),
                muscles = listOf(getString(R.string.muscle_legs), getString(R.string.muscle_back)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "stiff leg deadlift"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_9),
                description = getString(R.string.exercise_legs_9_desc),
                muscles = listOf(getString(R.string.muscle_legs), getString(R.string.muscle_back)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "romanian deadlift"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_10),
                description = getString(R.string.exercise_legs_10_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "hip adduction"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_11),
                description = getString(R.string.exercise_legs_11_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "hip abduction"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_12),
                description = getString(R.string.exercise_legs_12_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "sumo squat"
            ),
            Exercise(
                name = getString(R.string.exercise_legs_13),
                description = getString(R.string.exercise_legs_13_desc),
                muscles = listOf(getString(R.string.muscle_legs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "hack squat"
            )
        )
    }

    private fun getShoulderExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_shoulders_1),
                description = getString(R.string.exercise_shoulders_1_desc),
                muscles = listOf(
                    getString(R.string.muscle_shoulders),
                    getString(R.string.muscle_arms)
                ),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "military press"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_2),
                description = getString(R.string.exercise_shoulders_2_desc),
                muscles = listOf(getString(R.string.muscle_shoulders)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "dumbbell shoulder press"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_3),
                description = getString(R.string.exercise_shoulders_3_desc),
                muscles = listOf(getString(R.string.muscle_shoulders)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "lateral raise"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_4),
                description = getString(R.string.exercise_shoulders_4_desc),
                muscles = listOf(getString(R.string.muscle_shoulders)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "front raise"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_5),
                description = getString(R.string.exercise_shoulders_5_desc),
                muscles = listOf(
                    getString(R.string.muscle_shoulders),
                    getString(R.string.muscle_traps)
                ),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "upright row"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_6),
                description = getString(R.string.exercise_shoulders_6_desc),
                muscles = listOf(getString(R.string.muscle_shoulders)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "arnold press"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_7),
                description = getString(R.string.exercise_shoulders_7_desc),
                muscles = listOf(
                    getString(R.string.muscle_shoulders),
                    getString(R.string.muscle_back)
                ),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_cable),
                youtubeSearchTerm = "face pull"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_8),
                description = getString(R.string.exercise_shoulders_8_desc),
                muscles = listOf(getString(R.string.muscle_shoulders)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "reverse fly"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_9),
                description = getString(R.string.exercise_shoulders_9_desc),
                muscles = listOf(getString(R.string.muscle_shoulders)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "rear delt fly"
            ),
            Exercise(
                name = getString(R.string.exercise_shoulders_10),
                description = getString(R.string.exercise_shoulders_10_desc),
                muscles = listOf(getString(R.string.muscle_shoulders)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "seated shoulder press"
            )
        )
    }

    private fun getArmExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_arms_1),
                description = getString(R.string.exercise_arms_1_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "barbell curl"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_2),
                description = getString(R.string.exercise_arms_2_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "alternating dumbbell curl"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_3),
                description = getString(R.string.exercise_arms_3_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "hammer curl"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_4),
                description = getString(R.string.exercise_arms_4_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "preacher curl"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_5),
                description = getString(R.string.exercise_arms_5_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "concentration curl"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_6),
                description = getString(R.string.exercise_arms_6_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_cable),
                youtubeSearchTerm = "triceps pushdown"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_7),
                description = getString(R.string.exercise_arms_7_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "skull crusher"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_8),
                description = getString(R.string.exercise_arms_8_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "overhead triceps extension"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_9),
                description = getString(R.string.exercise_arms_9_desc),
                muscles = listOf(getString(R.string.muscle_arms), getString(R.string.muscle_chest)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "bench dip"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_10),
                description = getString(R.string.exercise_arms_10_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "reverse curl"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_11),
                description = getString(R.string.exercise_arms_11_desc),
                muscles = listOf(getString(R.string.muscle_arms)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "triceps kickback"
            ),
            Exercise(
                name = getString(R.string.exercise_arms_12),
                description = getString(R.string.exercise_arms_12_desc),
                muscles = listOf(getString(R.string.muscle_arms), getString(R.string.muscle_chest)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "close grip bench press"
            )
        )
    }

    private fun getAbsExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_abs_1),
                description = getString(R.string.exercise_abs_1_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "plank"
            ),
            Exercise(
                name = getString(R.string.exercise_abs_2),
                description = getString(R.string.exercise_abs_2_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "crunch"
            ),
            Exercise(
                name = getString(R.string.exercise_abs_3),
                description = getString(R.string.exercise_abs_3_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "leg raise"
            ),
            Exercise(
                name = getString(R.string.exercise_abs_4),
                description = getString(R.string.exercise_abs_4_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "side plank"
            ),
            Exercise(
                name = getString(R.string.exercise_abs_5),
                description = getString(R.string.exercise_abs_5_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "mountain climbers"
            ),
            Exercise(
                name = getString(R.string.exercise_abs_6),
                description = getString(R.string.exercise_abs_6_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "russian twist"
            ),
            Exercise(
                name = getString(R.string.exercise_abs_7),
                description = getString(R.string.exercise_abs_7_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_advanced),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "v ups"
            ),
            Exercise(
                name = getString(R.string.exercise_abs_8),
                description = getString(R.string.exercise_abs_8_desc),
                muscles = listOf(getString(R.string.muscle_abs)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "jackknife sit up"
            )
        )
    }

    private fun getCalvesExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_calves_1),
                description = getString(R.string.exercise_calves_1_desc),
                muscles = listOf(getString(R.string.muscle_calves)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "standing calf raise"
            ),
            Exercise(
                name = getString(R.string.exercise_calves_2),
                description = getString(R.string.exercise_calves_2_desc),
                muscles = listOf(getString(R.string.muscle_calves)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "seated calf raise"
            ),
            Exercise(
                name = getString(R.string.exercise_calves_3),
                description = getString(R.string.exercise_calves_3_desc),
                muscles = listOf(getString(R.string.muscle_calves)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "leg press calf raise"
            ),
            Exercise(
                name = getString(R.string.exercise_calves_4),
                description = getString(R.string.exercise_calves_4_desc),
                muscles = listOf(getString(R.string.muscle_calves)),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "donkey calf raise"
            ),
            Exercise(
                name = getString(R.string.exercise_calves_5),
                description = getString(R.string.exercise_calves_5_desc),
                muscles = listOf(getString(R.string.muscle_calves)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_bodyweight),
                youtubeSearchTerm = "single leg calf raise"
            ),
            Exercise(
                name = getString(R.string.exercise_calves_6),
                description = getString(R.string.exercise_calves_6_desc),
                muscles = listOf(getString(R.string.muscle_calves)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_machine),
                youtubeSearchTerm = "smith machine calf raise"
            )
        )
    }

    private fun getTrapsExercises(): List<Exercise> {
        return listOf(
            Exercise(
                name = getString(R.string.exercise_traps_1),
                description = getString(R.string.exercise_traps_1_desc),
                muscles = listOf(getString(R.string.muscle_traps)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "barbell shrug"
            ),
            Exercise(
                name = getString(R.string.exercise_traps_2),
                description = getString(R.string.exercise_traps_2_desc),
                muscles = listOf(getString(R.string.muscle_traps)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "dumbbell shrug"
            ),
            Exercise(
                name = getString(R.string.exercise_traps_3),
                description = getString(R.string.exercise_traps_3_desc),
                muscles = listOf(
                    getString(R.string.muscle_traps),
                    getString(R.string.muscle_shoulders)
                ),
                difficulty = getString(R.string.difficulty_intermediate),
                equipment = getString(R.string.equipment_barbell),
                youtubeSearchTerm = "upright row"
            ),
            Exercise(
                name = getString(R.string.exercise_traps_4),
                description = getString(R.string.exercise_traps_4_desc),
                muscles = listOf(
                    getString(R.string.muscle_traps),
                    getString(R.string.muscle_shoulders)
                ),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_cable),
                youtubeSearchTerm = "face pull"
            ),
            Exercise(
                name = getString(R.string.exercise_traps_5),
                description = getString(R.string.exercise_traps_5_desc),
                muscles = listOf(getString(R.string.muscle_traps)),
                difficulty = getString(R.string.difficulty_beginner),
                equipment = getString(R.string.equipment_dumbbell),
                youtubeSearchTerm = "single arm shrug"
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    class ExerciseAdapter(
        private val exercises: List<Exercise>,
        private val onItemClick: (Exercise) -> Unit
    ) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

        inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameView: TextView = itemView.findViewById(android.R.id.text1)

            init {
                itemView.setOnClickListener {
                    onItemClick(exercises[adapterPosition])
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ExerciseViewHolder(view)
        }

        override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
            val exercise = exercises[position]
            holder.nameView.text = exercise.name
        }

        override fun getItemCount(): Int = exercises.size
    }
}
