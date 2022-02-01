package com.example.rickmortymvvm.models;

import java.io.Serializable;

public class Character implements Serializable {
    private final int id;
    private final String name;
    private final String status;
    private final String image;
    private final String species;
    private final String type;
    private final String gender;
    private final String created;
    private Location location;
    private Origin origin;

    public Character(int id, String name, String status, String image, String species, String type, String gender, String created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.image = image;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.created = created;
    }

    public String getId() {
        return id+"";
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return  status;
    }

    public String getImage() {
        return image;
    }

    public String getSpecies() {
        return  species;
    }

    public String getType() {
        return  type;
    }

    public String getGender() {
        return gender;
    }

    public String getCreated() {
        return  created;
    }

    public Location getLocatoin() {
        return  location;
    }

    public Origin getOrigin() {
        return origin;
    }
}
