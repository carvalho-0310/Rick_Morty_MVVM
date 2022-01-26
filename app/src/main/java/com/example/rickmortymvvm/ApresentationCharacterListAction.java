package com.example.rickmortymvvm;

public abstract class ApresentationCharacterListAction {
     public static class GoToInfo extends ApresentationCharacterListAction {
          private final Character character;

          public GoToInfo(Character character) {
               this.character = character;
          }

          public Character getCharacter() {
               return character;
          }
     }

     public static class Finish extends ApresentationCharacterListAction {

     }
}
