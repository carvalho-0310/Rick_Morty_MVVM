package com.example.rickmortymvvm;

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
