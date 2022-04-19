package com.example.rickmortymvvm.data.local.character

import com.example.rickmortymvvm.data.local.models.CharacterLocal
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.models.Location
import com.example.rickmortymvvm.models.Origin
import io.reactivex.Observable

class CharacterDataLocalImpl(
    private val characterLocalDao: CharacterLocalDao
) : CharacterDataLocal {

    override fun saveCharacters(list: List<Character>) {
        list.forEach {
            characterLocalDao.savaCharacter(
                CharacterLocal(
                    it.id,
                    it.name ?: "",
                    it.status,
                    it.image,
                    it.species,
                    it.type,
                    it.gender,
                    it.created,
                    it.location.name ?: "",
                    it.location.url ?: "",
                    it.origin.name ?: "",
                    it.origin.url ?: ""
                )
            )
        }
    }

    override fun getCharacters(): Observable<List<Character>> {
        return characterLocalDao.getList().map {
            it.map { characterLocal ->
                Character(
                    characterLocal.id,
                    characterLocal.name,
                    characterLocal.status,
                    characterLocal.image,
                    characterLocal.species,
                    characterLocal.type,
                    characterLocal.gender,
                    characterLocal.created,
                    Location(characterLocal.locationName, characterLocal.locationUrl),
                    Origin(characterLocal.originName, characterLocal.originUrl)
                )
            }
        }
    }
}
