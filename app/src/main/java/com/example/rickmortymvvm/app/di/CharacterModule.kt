package com.example.rickmortymvvm.di

import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListViewModelImpl
import com.example.rickmortymvvm.services.CharacterRepositoryImpl
import com.example.rickmortymvvm.services.CharacterDataRemoteImpl
import com.example.rickmortymvvm.services.CharacterDataRemote
import com.example.rickmortymvvm.services.CharacterRepository
import com.example.rickmortymvvm.services.CharacterService
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val characterModule = module {
    viewModel {
        PresentationCharacterListViewModelImpl(get(), get())
    }
    factory { CompositeDisposable() }
    factory<CharacterRepository> { CharacterRepositoryImpl(get()) }
    factory<CharacterDataRemote> { CharacterDataRemoteImpl(get()) }
    factory { get<Retrofit>().create(CharacterService::class.java) }
}
