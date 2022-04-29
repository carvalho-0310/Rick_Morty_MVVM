package com.example.rickmortymvvm.app.di

import com.example.rickmortymvvm.data.remote.MapperRemoteImpl
import com.example.rickmortymvvm.data.remote.CharacterDataRemoteImpl
import com.example.rickmortymvvm.data.remote.CharacterDataRemote
import com.example.rickmortymvvm.data.remote.CharacterService
import com.example.rickmortymvvm.presentation.PresentationCharacterListViewModelImpl
import com.example.rickmortymvvm.data.repository.CharacterRepositoryImpl
import com.example.rickmortymvvm.data.repository.CharacterRepository
import com.example.rickmortymvvm.data.repository.MapperRepositoryImpl
import com.example.rickmortymvvm.presentation.MapperViewModelImpl
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val characterModule = module {
    viewModel {
        PresentationCharacterListViewModelImpl(get(), get(), MapperViewModelImpl())
    }
    factory { CompositeDisposable() }
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), MapperRepositoryImpl()) }
    factory<CharacterDataRemote> { CharacterDataRemoteImpl(get(), MapperRemoteImpl()) }
    factory { get<Retrofit>().create(CharacterService::class.java) }
}
