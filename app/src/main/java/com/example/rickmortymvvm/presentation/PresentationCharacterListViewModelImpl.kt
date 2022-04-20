package com.example.rickmortymvvm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickmortymvvm.presentation.PresentationCharacterListAction.Finish
import com.example.rickmortymvvm.presentation.PresentationCharacterListAction.GoToInfo
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.data.repository.CharacterRepository
import com.example.rickmortymvvm.app.util.observer.MutableAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PresentationCharacterListViewModelImpl(
    private val disposable: CompositeDisposable,
    private val repository: CharacterRepository
) : PresentationCharacterListViewModel, ViewModel() {

    private var list: MutableList<Character> = ArrayList()
    private var requestAvailable = true

    private var setUp = true
    private val _action = MutableAction<PresentationCharacterListAction>()
    val action: LiveData<PresentationCharacterListAction>
        get() = _action

    private val _status = MutableLiveData(
        PresentationCharacterListState(true, list, false, false)
    )
    val state: LiveData<PresentationCharacterListState>
        get() = _status

    override fun setUp() {
        if (setUp) {
            requestCharacterList()
            setUp = false
        }
    }

    override fun onClickCharacter(character: Character) {
        _action.sendAction(GoToInfo(character))
    }

    override fun onClickTryAgain() {
        requestCharacterList()
    }

    override fun onClickQuit() {
        _action.value = Finish
    }

    override fun onScrollFinal() {
        requestCharacterList()
    }

    private fun requestCharacterList() {
        if (requestAvailable) {
            requestAvailable = false
            _status.value = PresentationCharacterListState(
                true,
                list,
                true,
                false
            )

            disposable.add(
                repository.getListCharacter()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ results ->
                        if (results != null) {
                            list.addAll(results)
                            _status.value = PresentationCharacterListState(
                                false,
                                list,
                                true,
                                false
                            )
                            requestAvailable = true
                        }
                    }) {
                        _status.value = PresentationCharacterListState(
                            false,
                            list,
                            false,
                            true
                        )
                        requestAvailable = true
                    }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
