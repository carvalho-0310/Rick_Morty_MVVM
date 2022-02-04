package com.example.rickmortymvvm.list.viewmodel;

import com.example.rickmortymvvm.models.Character;

public abstract class PresentationCharacterListAction {
    public static class GoToInfo extends PresentationCharacterListAction {
        private final Character character;

        public GoToInfo(Character character) {
            this.character = character;
        }

        public Character getCharacter() {
            return character;
        }
    }

    public static class Finish extends PresentationCharacterListAction {

    }
}
