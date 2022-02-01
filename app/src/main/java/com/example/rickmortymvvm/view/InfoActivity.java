package com.example.rickmortymvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;

import com.example.rickmortymvvm.R;
import com.example.rickmortymvvm.models.Character;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        Character character = (Character) getIntent().getSerializableExtra("c");
        assert character != null;
        Toast.makeText(this, character.getName(), Toast.LENGTH_LONG).show();
        ImageView imageView = findViewById(R.id.image_info);
        TextView nameView = findViewById(R.id.name_info);
        TextView statusView = findViewById(R.id.status_info);
        TextView speciesView = findViewById(R.id.species_info);
        TextView genderView = findViewById(R.id.gender_info);
        TextView idView = findViewById(R.id.id_character);
        TextView originView = findViewById(R.id.origin_info);
        TextView localization = findViewById(R.id.localization_info);
        TextView easterEggView = findViewById(R.id.easter_egg);

        Glide.with(imageView)
                .load(character.getImage())
                .into(imageView);
        nameView.setText(getString(R.string.name, character.getName()));
        idView.setText(getString(R.string.id, character.getId()));
        statusView.setText(getString(R.string.status,character.getStatus()));
        speciesView.setText(getString(R.string.species, character.getSpecies()));
        genderView.setText(getString(R.string.gender, character.getGender()));
        originView.setText(getString(R.string.origin, character.getOrigin().getName()));
        localization.setText(getString(R.string.location, character.getLocatoin().getName()));
        if (character.getStatus().contains("Dead")) {

            int color = ContextCompat.getColor(statusView.getContext(), R.color.colorRedTrans);
            statusView.setBackgroundColor(color);
        } else if (character.getStatus().contains("Alive")) {
            int color = ContextCompat.getColor(statusView.getContext(), R.color.colorgreemTrans);
            statusView.setBackgroundColor(color);
        }
        if (character.getName().contains("Rick Sanchez")) {
            easterEggView.setText(getString(R.string.easter_egg));

        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}