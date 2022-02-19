package com.example.rickmortymvvm.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortymvvm.list.services.CharacterService
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction.Finish
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction.GoToInfo
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.util.observer.MutableAction
import com.example.rickmortymvvm.util.observer.MutableMyObservable
import com.example.rickmortymvvm.util.observer.MutableMyObservableImpl
import com.example.rickmortymvvm.util.observer.MyObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelImpl : MyViewModel, ViewModel() {

    private var page = 1
    private var pages = 1
    private var requestAvailable = true
    private val rf = Retrofit.Builder()
        .baseUrl(CharacterService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    private val service = rf.create(CharacterService::class.java)
    private val mutableObservable: MutableMyObservable = MutableMyObservableImpl()
    private var list: MutableList<Character> = ArrayList()

    private val _action = MutableAction<PresentationCharacterListAction>()
    val action :LiveData<PresentationCharacterListAction>
        get() = _action

    private val _status = MutableLiveData(
        PresentationCharacterListState(true, list, false, false)
    )
    val state: LiveData<PresentationCharacterListState>
        get() = _status

    override fun onCreate() {
        if (list.size == 0) {
            requestCharacterList()
        }
    }

    override fun onClickCharacter(character: Character?) {
        _action.sendAction( GoToInfo(character!!))
    }

    override fun onClickTryAgain() {
        requestCharacterList()
    }

    override fun onClickQuit() {
        _action.value = Finish
    }

    override val myObservable: MyObservable
        get() = mutableObservable

    override fun onScrollFinal() {
        requestCharacterList()
    }

    private fun requestCharacterList() {
        val TAG = "service"
        if (page <= pages && requestAvailable) {
            requestAvailable = false
            _status.value = PresentationCharacterListState(true, list, true, false)
            val disposable = service.listCharacter(page)
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
                        pages = response.info!!.pages
                        page++
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

        }
    }
}






