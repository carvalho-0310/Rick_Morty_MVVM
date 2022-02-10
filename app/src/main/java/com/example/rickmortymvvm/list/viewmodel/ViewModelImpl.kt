package com.example.rickmortymvvm.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rickmortymvvm.list.services.CharacterService
import com.example.rickmortymvvm.list.services.models.CharacterResponseVO
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction.Finish
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction.GoToInfo
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.util.observer.MutableObservable
import com.example.rickmortymvvm.util.observer.MutableObservableImpl
import com.example.rickmortymvvm.util.observer.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelImpl : MyViewModel, ViewModel() {

    private var page = 1
    private var pages = 1
    private var requestAvailable = true
    private val rf = Retrofit.Builder()
        .baseUrl(CharacterService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = rf.create<CharacterService>(CharacterService::class.java)
    private val mutableObservable: MutableObservable = MutableObservableImpl()

    private val list: MutableList<Character> = ArrayList()

    override fun onCreate() {
        if (list.size == 0) {
            requestCharacterList()
        } else {
            mutableObservable.update(PresentationCharacterListState(false, list, true, false))
        }

    }

    override fun onClickCharacter(character: Character?) {
        mutableObservable.update(GoToInfo(character!!))
    }

    override fun onClickTryAgain() {
        requestCharacterList()
    }

    override fun onClickQuit() {
        mutableObservable.update(Finish())
    }

    override val observable: Observable
        get() = mutableObservable

    override fun onScrollFinal() {
        requestCharacterList()
    }

    private fun requestCharacterList() {
        val TAG = "service"
        if (page <= pages && requestAvailable) {
            requestAvailable = false
            mutableObservable.update(PresentationCharacterListState(true, list, true, false))
            service.listCharacter(page)
                ?.enqueue(
                    object : Callback<CharacterResponseVO?> {
                        override fun onResponse(
                            call: Call<CharacterResponseVO?>,
                            response: Response<CharacterResponseVO?>
                        ) {
                            val characterResponse: CharacterResponseVO? = response.body()
                            if (response.isSuccessful
                                && characterResponse != null
                            ) {
                                list.addAll(characterResponse.results!!)
                                mutableObservable.update(
                                    PresentationCharacterListState(
                                        false,
                                        list,
                                        true,
                                        false
                                    )
                                )
                                pages = characterResponse.info!!.pages
                                page++
                            } else {
                                Log.i(TAG, "Error: " + response.code())
                                mutableObservable.update(
                                    PresentationCharacterListState(
                                        false,
                                        list,
                                        false,
                                        true
                                    )
                                )
                            }
                            requestAvailable = true
                        }

                        override fun onFailure(call: Call<CharacterResponseVO?>, t: Throwable) {
                            Log.e(TAG, "Error : " + t.message)
                            mutableObservable.update(
                                PresentationCharacterListState(
                                    false,
                                    list,
                                    false,
                                    true
                                )
                            )
                            requestAvailable = true
                        }
                    })
        }
    }
}



