package com.example.rickmortymvvm.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.rickmortymvvm.intrefaces.CharacterService;
import com.example.rickmortymvvm.intrefaces.MutableObservable;
import com.example.rickmortymvvm.intrefaces.Observable;
import com.example.rickmortymvvm.intrefaces.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.example.rickmortymvvm.models.Character;
import com.example.rickmortymvvm.models.CharacterResponseVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rickmortymvvm.services.PresentationCharacterListAction.*;

public class ViewModelImpl implements ViewModel {

    private int page = 1;
    private int pages = 1;

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

    private void requestCharacterList() {
        final String TAG = "service";
        if (page <= pages) {
            mutableObservable.update(new PresentationCharacterListState(true, list, true, false));
            service.listCharacter(page)
                    .enqueue(
                            new Callback<CharacterResponseVO>() {
                                @Override
                                public void onResponse(@NonNull Call<CharacterResponseVO> call, @NonNull Response<CharacterResponseVO> response) {
                                    if (!response.isSuccessful()) {
                                        Log.i(TAG, "Error: " + response.code());

                                        mutableObservable.update(new PresentationCharacterListState(false, list, false, true));

                                    } else {
                                        CharacterResponseVO characterResponse = response.body();
                                        assert characterResponse != null;
                                        list.addAll(characterResponse.getResults());
                                        mutableObservable.update(new PresentationCharacterListState(false, list, true, false));
                                        pages = characterResponse.getInfo().getPages();
                                        page++;
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<CharacterResponseVO> call, @NonNull Throwable t) {
                                    Log.e(TAG, "Error : " + t.getMessage());
                                    mutableObservable.update(new PresentationCharacterListState(false, list, false, true));
                                }
                            });

        }
    }
}

