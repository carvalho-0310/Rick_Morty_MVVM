package com.example.rickmortymvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmortymvvm.R
import com.example.rickmortymvvm.databinding.ModelMainActivityBinding
import com.example.rickmortymvvm.presentation.models.CharacterVM
import com.example.rickmortymvvm.app.util.adapter.DiffUtilGeneric

class ListCharacterAdapter(private val presentationCharacterListActivity: PresentationCharacterListActivity) :
    RecyclerView.Adapter<ListCharacterAdapter.ViewHolder>() {
    private val list: MutableList<CharacterVM> = ArrayList()

    fun setListAdapter(newList: List<CharacterVM>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilGeneric(list, newList))
        list.clear()
        list.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ModelMainActivityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, presentationCharacterListActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(binding: ModelMainActivityBinding, clickCharacter: OnClickCharacter) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.image
        private val nameView = binding.name
        private val statusView = binding.status
        private lateinit var characterVM: CharacterVM

        fun bind(characterVM: CharacterVM) {
            this.characterVM = characterVM
            Glide.with(imageView)
                .load(characterVM.image)
                .into(imageView)
            nameView.text = characterVM.name
            statusView.text = characterVM.status

            if (characterVM.status == "Dead") {
                val color = ContextCompat.getColor(statusView.context, R.color.colorRed)
                statusView.setTextColor(color)
            } else if (characterVM.status == "Alive") {
                val color = ContextCompat.getColor(statusView.context, R.color.colorgreem)
                statusView.setTextColor(color)
            } else {
                val color = ContextCompat.getColor(statusView.context, R.color.colorBlck)
                statusView.setTextColor(color)
            }
        }

        init {
            binding.root.setOnClickListener {
                clickCharacter.onClickCharacter(characterVM)
            }
        }
    }
}
