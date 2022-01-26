package com.example.rickmortymvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rickmortymvvm.intrefaces.Observer;
import com.example.rickmortymvvm.intrefaces.OnClickCharacter;
import com.example.rickmortymvvm.intrefaces.ViewModel;

import java.util.List;

import static com.example.rickmortymvvm.ApresentationCharacterListAction.*;

public class ApresentationCharacterListActivity extends AppCompatActivity implements OnClickCharacter, Observer {

    private ListCharacterAdapter characterListAdapter = new ListCharacterAdapter(this);
    private ViewModel viewModel = new ViewModelImpl();
    private ProgressBar pbLoading;
    private RecyclerView rvCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel.getObservable().register(this);

        rvCharacter = findViewById(R.id.rv_character);
        rvCharacter.setAdapter(characterListAdapter);
        rvCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.onClickTryAgain();
                }
            }
        });

        pbLoading = findViewById(R.id.main_pb_loading);

        viewModel.onCreate();

    }

    public void onClickCharacter(Character character) {
        viewModel.onClickCharacter(character);
    }

    @Override
    public void notify(ApresentatationCharacterListState state) {
        setupLoading(state.isLoadingVisible());
        setCharacterList(state.getListChracter());
        setupListCharacter(state.isListCharacterVisible());
        setupModalError(state.isShowModalErrorVisible());
    }

    private void setupLoading(boolean isLoadingVisible) {
        if (isLoadingVisible) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }
    }

    private void setCharacterList(List<Character> characterList) {
        characterListAdapter.setListAdapter(characterList);
    }

    private void setupListCharacter(boolean isListCharacterVisible) {
        if (isListCharacterVisible) {
            rvCharacter.setVisibility(View.VISIBLE);
        } else {
            rvCharacter.setVisibility(View.GONE);
        }
    }

    private void setupModalError(boolean isShowModalErrorVisible) {
        if (isShowModalErrorVisible) {
            new AlertDialog.Builder(this)
                    .setTitle("Porra Morty")
                    .setMessage("Você ta sem internet, não fode")
                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            viewModel.onClickTryAgain();
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ApresentationCharacterListActivity.this, "Porra Morty você fudeo comigo", Toast.LENGTH_SHORT).show();
                            viewModel.onClickQuit();
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        }
    }

    @Override
    public void notify(ApresentationCharacterListAction action) {
        if (action instanceof GoToInfo) {
            Character character = ((GoToInfo) action).getCharacter();
            startInfo(character);
        } else if (action instanceof Finish) {
            finish();
        }
    }

    private void startInfo(Character character) {
        Intent intent = new Intent(this, InfosActivity.class);
        intent.putExtra("c", character);
        startActivity(intent);
    }
}