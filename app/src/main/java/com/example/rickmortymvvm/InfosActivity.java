package com.example.rickmortymvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class InfosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        Character character = (Character) getIntent().getSerializableExtra("c");
        Toast.makeText(this, character.getName(), Toast.LENGTH_LONG).show();
        ImageView imageView = findViewById(R.id.image_info);
        TextView nameView = findViewById(R.id.name_info);
        TextView statusView = findViewById(R.id.status_info);
        TextView speciesView = findViewById(R.id.species_info);
        TextView genderView = findViewById(R.id.gender_info);
        TextView idView = findViewById(R.id.id_character);
        TextView originView = findViewById(R.id.origin_info);
        TextView localizacaoView = findViewById(R.id.localizacao_info);
        TextView easterEggView = findViewById(R.id.easter_egg);

        Glide.with(imageView)
                .load(character.getImage())
                .into(imageView);
        nameView.setText("Name: " + character.getName());
        idView.setText("Id: " + character.getId());
        speciesView.setText("Species: " + character.getSpecies());
        genderView.setText("Gender: " + character.getGender());
        originView.setText("Origin: " + character.getOrigin().getName());
        localizacaoView.setText("Location: " + character.getLocatoin().getName());
        if (character.getStatus().contains("Dead")) {
            int color = ContextCompat.getColor(statusView.getContext(), R.color.colorRedTrans);
            statusView.setBackgroundColor(color);
        } else if (character.getStatus().contains("Alive")) {
            int color = ContextCompat.getColor(statusView.getContext(), R.color.colorgreemTrans);
            statusView.setBackgroundColor(color);
        }
        if (character.getName().contains("Rick Sanchez")){
            easterEggView.setText("This guy is the most fucked up of all universes");

        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}