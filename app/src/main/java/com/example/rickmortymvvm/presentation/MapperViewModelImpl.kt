package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.presentation.models.CharacterVM
import com.example.rickmortymvvm.presentation.models.LocationVM
import com.example.rickmortymvvm.presentation.models.OriginVM

class MapperViewModelImpl : MapperViewModel {
    override fun characterFromCharacterVm(character: Character): CharacterVM {
        return CharacterVM(
            character.id,
            character.name,
            character.status,
            character.image,
            character.species,
            character.type,
            character.gender,
            character.created,
            LocationVM(character.location.name, character.location.url),
            OriginVM(character.origin.name, character.origin.url)
        )
    }
}
