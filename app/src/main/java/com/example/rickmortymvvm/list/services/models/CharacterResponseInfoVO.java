package com.example.rickmortymvvm.list.services.models;

import com.example.rickmortymvvm.models.Character;

import java.util.List;

public class CharacterResponseInfoVO {
    private CharacterResponseInfoVO info;
    private List<Character> results;
    private int pages;

    public int getPages() {
        return pages;
    }

    public CharacterResponseInfoVO getInfo() {
        return info;
    }

    public List<Character> getResults() {
        return results;
    }
}

