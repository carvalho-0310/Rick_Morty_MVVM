package com.example.rickmortymvvm.app.di

import com.example.rickmortymvvm.app.AppDatabase
import com.example.rickmortymvvm.data.local.CharacterDataLocal
import com.example.rickmortymvvm.data.local.CharacterDataLocalImpl
import com.example.rickmortymvvm.data.local.MapperLocalImpl
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
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val characterModule = module {
    viewModel {
        PresentationCharacterListViewModelImpl(get(), get(), MapperViewModelImpl())
    }
    factory { CompositeDisposable() }
    factory <CharacterDataLocal> { CharacterDataLocalImpl(get(), MapperLocalImpl()) }
    factory { AppDatabase.getInstance(this.androidContext()).characterDao() }
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), MapperRepositoryImpl(), get()) }
    factory<CharacterDataRemote> { CharacterDataRemoteImpl(get(), MapperRemoteImpl()) }
    factory { get<Retrofit>().create(CharacterService::class.java) }
}
