package com.example.rickmortymvvm;

import android.util.Log;


import com.example.rickmortymvvm.intrefaces.CharacterSevice;
import com.example.rickmortymvvm.intrefaces.MutableObservable;
import com.example.rickmortymvvm.intrefaces.Observable;
import com.example.rickmortymvvm.intrefaces.ViewModel;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rickmortymvvm.ApresentationCharacterListAction.*;

public class ViewModelImpl implements ViewModel {

    private int page = 1;
    private Retrofit rf = new Retrofit.
            Builder().
            baseUrl(CharacterSevice.BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            build();
    private CharacterSevice service = rf.create(CharacterSevice.class);
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
        if (page <= 42) {
            mutableObservable.update(new ApresentatationCharacterListState(true, list, true, false));
            service.listCharacter(page)
                    .enqueue(
                            new Callback<CharacterResponseVO>() {
                                @Override
                                public void onResponse(Call<CharacterResponseVO> call, Response<CharacterResponseVO> response) {
                                    if (!response.isSuccessful()) {
                                        Log.i(TAG, "Erro: " + response.code());
                                        mutableObservable.update(new ApresentatationCharacterListState(false, list, false, true));

                                    } else {
                                        CharacterResponseVO characterResponse = response.body();
                                        mutableObservable.update(new ApresentatationCharacterListState(false, characterResponse.getResults(), true, false));
                                        page++;
                                    }
                                }
                                @Override
                                public void onFailure(Call<CharacterResponseVO> call, Throwable t) {
                                    Log.e(TAG, "Erro: " + t.getMessage());
                                    mutableObservable.update(new ApresentatationCharacterListState(false, list, false, true));
                                }
                            });

        }
    }
}

