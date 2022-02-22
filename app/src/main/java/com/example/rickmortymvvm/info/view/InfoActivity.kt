package com.example.rickmortymvvm.info.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.rickmortymvvm.R
import com.example.rickmortymvvm.models.Character
import java.util.Objects

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infos)
        val character = (intent.getSerializableExtra("c") as Character?)!!
        Toast.makeText(this, character.name, Toast.LENGTH_LONG).show()
        val imageView = findViewById<ImageView>(R.id.image_info)
        val nameView = findViewById<TextView>(R.id.name_info)
        val statusView = findViewById<TextView>(R.id.status_info)
        val speciesView = findViewById<TextView>(R.id.species_info)
        val genderView = findViewById<TextView>(R.id.gender_info)
        val idView = findViewById<TextView>(R.id.id_character)
        val originView = findViewById<TextView>(R.id.origin_info)
        val localization = findViewById<TextView>(R.id.localization_info)
        val easterEggView = findViewById<TextView>(R.id.easter_egg)
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
        if (false) {
            easterEggView.text = getString(R.string.easter_egg)
        }
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
