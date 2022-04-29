package com.example.rickmortymvvm.data.local

import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import io.reactivex.Observable

class CharacterDataLocalImpl(
    private val characterLocalDao: CharacterLocalDao,
    private val mapperLocal: MapperLocal
) : CharacterDataLocal {

    override fun saveCharacters(list: List<CharacterRepositoryInfos>) {
        list.forEach {
            characterLocalDao.savaCharacter(mapperLocal.characterRepositoryInfosFromCharacterLocal(it))
        }
    }

    override fun getCharacters(): Observable<List<CharacterRepositoryInfos>> {
        return characterLocalDao.getList().map {
            it.map { characterLocal -> mapperLocal.characterLocalFromInfosRepository(characterLocal) }
        }
    }

    override fun clearLocalList() {
        characterLocalDao.clearLocalList()
    }
}
