package com.example.rickmortymvvm.list.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmortymvvm.R
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.util.adapter.DiffUtilGeneric

class ListCharacterAdapter(private val presentationCharacterListActivity: PresentationCharacterListActivity) :
    RecyclerView.Adapter<ListCharacterAdapter.ViewHolder>() {
    private val list: MutableList<Character> = ArrayList()

    fun setListAdapter(newList: List<Character>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilGeneric(list, newList))
        list.clear()
        list.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.model_main_activity, parent, false)
        return ViewHolder(view, presentationCharacterListActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View, clickCharacter: OnClickCharacter) :
        RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        private val nameView: TextView
        private val statusView: TextView
        private var character: Character? = null
        fun bind(character: Character) {
            this.character = character
            Glide.with(imageView)
                .load(character.image)
                .into(imageView)
            nameView.text = character.name
            statusView.text = character.status
            if (character.status == "Dead") {
                val color = ContextCompat.getColor(statusView.context, R.color.colorRed)
                statusView.setTextColor(color)
            } else if (character.status == "Alive") {
                val color = ContextCompat.getColor(statusView.context, R.color.colorgreem)
                statusView.setTextColor(color)
            } else {
                val color = ContextCompat.getColor(statusView.context, R.color.colorBlck)
                statusView.setTextColor(color)
            }
        }

        init {
            imageView = itemView.findViewById(R.id.image)
            nameView = itemView.findViewById(R.id.name)
            statusView = itemView.findViewById(R.id.status)
            itemView.setOnClickListener { v: View? ->
                clickCharacter.onClickCharacter(character)
            }
        }
    }
}