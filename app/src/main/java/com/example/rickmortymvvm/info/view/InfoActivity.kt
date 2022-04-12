package com.example.rickmortymvvm.info.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.rickmortymvvm.R
import com.example.rickmortymvvm.databinding.ActivityInfosBinding
import com.example.rickmortymvvm.models.Character
import java.util.Objects

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val character = (intent.getSerializableExtra("c") as Character)
        val imageView = binding.imageInfo
        val nameView = binding.nameInfo
        val statusView = binding.statusInfo
        val speciesView = binding.speciesInfo
        val genderView = binding.genderInfo
        val idView = binding.idCharacter
        val originView = binding.originInfo
        val localization = binding.localizationInfo

        Glide.with(imageView)
            .load(character.image)
            .into(imageView)
        nameView.text = getString(R.string.name, character.name)
        idView.text = getString(R.string.id, character.id)
        statusView.text = getString(R.string.status, character.status)
        speciesView.text = getString(R.string.species, character.species)
        genderView.text = getString(R.string.gender, character.gender)
        originView.text =
            getString(R.string.origin, Objects.requireNonNull(character.origin?.name))
        localization.text =
            getString(R.string.location, Objects.requireNonNull(character.location?.name))
        if (character.status.contains("Dead")) {
            val color = ContextCompat.getColor(statusView.context, R.color.colorRedTrans)
            statusView.setBackgroundColor(color)
        } else if (character.status.contains("Alive")) {
            val color = ContextCompat.getColor(statusView.context, R.color.colorgreemTrans)
            statusView.setBackgroundColor(color)
        }

        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
