package com.example.guia_pocket_app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.guia_pocket_app.R
import com.example.guia_pocket_app.model.Muscle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MuscleAdapter(
    private val muscles: List<Muscle>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>() {

    inner class MuscleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val muscleName: TextView = itemView.findViewById(R.id.muscleName)
        val muscleImage: ImageView = itemView.findViewById(R.id.muscleImage)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_muscle, parent, false)
        return MuscleViewHolder(view)
    }

    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        val muscle = muscles[position]
        holder.muscleName.text = muscle.name
        holder.muscleImage.setImageResource(muscle.iconResId)
    }

    override fun getItemCount(): Int = muscles.size
}

