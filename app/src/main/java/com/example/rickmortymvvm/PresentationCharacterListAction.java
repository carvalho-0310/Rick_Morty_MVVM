package com.example.rickmortymvvm;

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
