package com.example.guia_pocket_app.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guia_pocket_app.data.model.Exercise
import com.example.guia_pocket_app.databinding.ItemExerciseBinding

class ExerciseAdapter(
    private val exercises: List<Exercise>,
    private val onItemClick: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exercises[position])
    }

    override fun getItemCount() = exercises.size

    class ExerciseViewHolder(
        private val binding: ItemExerciseBinding,
        private val onItemClick: (Exercise) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            binding.apply {
                tvExerciseName.text = exercise.name
                tvExerciseDifficulty.text = exercise.difficulty
                tvExerciseEquipment.text = exercise.equipment

                // Carregar imagem se houver
                if (exercise.imageUri.isNotEmpty()) {
                    try {
                        ivExercise.setImageURI(Uri.parse(exercise.imageUri))
                    } catch (e: Exception) {
                        // Se não conseguir carregar, deixar vazio
                    }
                }

                // Click listener
                root.setOnClickListener {
                    onItemClick(exercise)
                }
            }
        }
    }
}