package com.example.rickmortymvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortymvvm.presentation.viewmodel.PresentationCharacterListAction.Finish
import com.example.rickmortymvvm.presentation.viewmodel.PresentationCharacterListAction.GoToInfo
import com.example.rickmortymvvm.presentation.models.Character
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
    private val _action = MutableAction<PresentationCharacterListAction>()
    val action: LiveData<PresentationCharacterListAction>
        get() = _action

    private val _status = MutableLiveData(
        PresentationCharacterListState(true, list, false, false)
    )
    val state: LiveData<PresentationCharacterListState>
        get() = _status

    init {
        requestCharacterList()
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
                    .subscribe({ response ->
                        val results = response?.results
                        if (results != null) {
                            list.addAll(response.results)
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
