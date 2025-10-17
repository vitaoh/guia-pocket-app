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

class MuscleAdapter(context: Context, private val muscles: List<Muscle>) :
    ArrayAdapter<Muscle>(context, 0, muscles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_muscle, parent, false)

        val muscle = muscles[position]
        val iconView = view.findViewById<ImageView>(R.id.muscleIcon)
        val nameView = view.findViewById<TextView>(R.id.muscleName)

        iconView.setImageResource(muscle.iconResId)
        nameView.text = muscle.name

        return view
    }
}
