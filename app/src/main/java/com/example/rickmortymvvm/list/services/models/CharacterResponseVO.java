package com.example.rickmortymvvm.list.services.models;

import com.example.rickmortymvvm.list.services.models.CharacterResponseInfoVO;
import com.example.rickmortymvvm.models.Character;

import java.util.List;

public class CharacterResponseVO {
    private CharacterResponseInfoVO info;
    private List<Character> results;

    public CharacterResponseInfoVO getInfo() {
        return info;
    }

    public List<Character> getResults() {
        return results;
    }
}
