package com.example.rickmortymvvm.list.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.rickmortymvvm.list.services.CharacterService;
import com.example.rickmortymvvm.util.observer.MutableObservable;
import com.example.rickmortymvvm.util.observer.Observable;

import java.util.ArrayList;
import java.util.List;

import com.example.rickmortymvvm.models.Character;
import com.example.rickmortymvvm.list.services.models.CharacterResponseVO;
import com.example.rickmortymvvm.util.observer.MutableObservableImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction.*;

public class ViewModelImpl implements ViewModel {

    private int page = 1;
    private int pages = 1;
    private boolean requestAvailable = true;

    private Retrofit rf = new Retrofit
            .Builder()
            .baseUrl(CharacterService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private CharacterService service = rf.create(CharacterService.class);
    private MutableObservable mutableObservable = new MutableObservableImpl();
    private List<Character> list = new ArrayList<>();

    @Override
    public void onCreate() {
        requestCharacterList();
    }

    @Override
    public void onClickCharacter(Character character) {
        mutableObservable.update(new GoToInfo(character));
    }

    @Override
    public void onClickTryAgain() {
        requestCharacterList();
    }

    @Override
    public void onClickQuit() {
        mutableObservable.update(new Finish());
    }



    public Observable getObservable() {
        return mutableObservable;
    }

    @Override
    public void onScrollFinal() {
        requestCharacterList();
    }


    private void requestCharacterList() {
        final String TAG = "service";
        if (page <= pages && requestAvailable) {
            requestAvailable = false;
            mutableObservable.update(new PresentationCharacterListState(true, list, true, false));
            service.listCharacter(page)
                    .enqueue(
                            new Callback<CharacterResponseVO>() {
                                @Override
                                public void onResponse(@NonNull Call<CharacterResponseVO> call, @NonNull Response<CharacterResponseVO> response) {
                                    CharacterResponseVO characterResponse;

                                    if (response.isSuccessful()
                                            && (characterResponse = response.body()) != null
                                    ) {
                                        list.addAll(characterResponse.getResults());
                                        mutableObservable.update(new PresentationCharacterListState(false, list, true, false));
                                        pages = characterResponse.getInfo().getPages();
                                        page++;
                                    } else {
                                        Log.i(TAG, "Error: " + response.code());

                                        mutableObservable.update(new PresentationCharacterListState(false, list, false, true));
                                    }
                                    requestAvailable = true;
                                }
                                @Override
                                public void onFailure(@NonNull Call<CharacterResponseVO> call, @NonNull Throwable t) {
                                    Log.e(TAG, "Error : " + t.getMessage());
                                    mutableObservable.update(new PresentationCharacterListState(false, list, false, true));
                                    requestAvailable = true;
                                }
                            });
        }
    }
}

