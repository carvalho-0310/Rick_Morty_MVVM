package com.example.rickmortymvvm.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickmortymvvm.R;
import com.example.rickmortymvvm.intrefaces.OnClickCharacter;

import java.util.ArrayList;
import java.util.List;

import com.example.rickmortymvvm.models.Character;
import com.example.rickmortymvvm.view.PresentationCharacterListActivity;

public class ListCharacterAdapter extends RecyclerView.Adapter<ListCharacterAdapter.ViewHolder> {

    private List<Character> list = new ArrayList<>();
    private PresentationCharacterListActivity presentationCharacterListActivity;


    public ListCharacterAdapter(PresentationCharacterListActivity presentationCharacterListActivity) {
        this.presentationCharacterListActivity = presentationCharacterListActivity;
    }

    public void setListAdapter(List<Character> listCharacter) {
        list =listCharacter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_main_activity, parent, false);
        return new ViewHolder(view, presentationCharacterListActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameView;
        private final TextView statusView;
        private Character character;

        public ViewHolder(@NonNull View itemView, OnClickCharacter clickCharacter) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            nameView = itemView.findViewById(R.id.name);
            statusView = itemView.findViewById(R.id.status);

        }


        public void bind(Character character) {
            this.character = character;
            Glide.with(imageView)
                    .load(character.getImage())
                    .into(imageView);
            nameView.setText(character.getName());
            statusView.setText(character.getStatus());
            if (character.getStatus().contains("Dead")) {
                int color = ContextCompat.getColor(statusView.getContext(), R.color.colorRed);
                statusView.setTextColor(color);
            } else if (character.getStatus().contains("Alive")) {
                int color = ContextCompat.getColor(statusView.getContext(), R.color.colorgreem);
                statusView.setTextColor(color);
            } else {
                int color = ContextCompat.getColor(statusView.getContext(), R.color.colorBlck);
                statusView.setTextColor(color);
            }
        }

    }
}
