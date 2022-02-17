package com.example.rickmortymvvm.list.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortymvvm.R
import com.example.rickmortymvvm.info.view.InfoActivity
import com.example.rickmortymvvm.intrefaces.Observer
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction.Finish
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction.GoToInfo
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState
import com.example.rickmortymvvm.list.viewmodel.ViewModelImpl
import com.example.rickmortymvvm.models.Character

class PresentationCharacterListActivity : AppCompatActivity(), OnClickCharacter, Observer {

    private val characterListAdapter = ListCharacterAdapter(this)
    private val myViewModel: ViewModelImpl by viewModels()
    private var pbLoading: ProgressBar? = null
    private var rvCharacter: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("inicio", "inicio da main")
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myViewModel.myObservable.register(this)
        rvCharacter = findViewById(R.id.rv_character)
        rvCharacter!!.adapter = characterListAdapter
        rvCharacter!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    myViewModel.onScrollFinal()
                }
            }
        })
        pbLoading = findViewById(R.id.main_pb_loading)
        myViewModel.onCreate()
        myViewModel.state.observe(this){
                NewStatus-> notify(NewStatus)
        }
    }
    override fun onClickCharacter(character: Character?) {

        myViewModel.onClickCharacter(character)
    }

    override fun notify(state: PresentationCharacterListState) {
        setupLoading(state.isLoadingVisible)
        setCharacterList(state.listCharacter)
        setupListCharacter(state.isListCharacterVisible)
        setupModalError(state.isShowModalErrorVisible)
    }

    private fun setupLoading(isLoadingVisible: Boolean) {
        if (isLoadingVisible) {
            pbLoading!!.visibility = View.VISIBLE
        } else {
            pbLoading!!.visibility = View.GONE
        }
    }

    private fun setCharacterList(characterList: List<Character>) {
        characterListAdapter.setListAdapter(characterList)
    }

    private fun setupListCharacter(isListCharacterVisible: Boolean) {
        if (isListCharacterVisible) {
            rvCharacter!!.visibility = View.VISIBLE
        } else {
            rvCharacter!!.visibility = View.GONE
        }
    }

    private fun setupModalError(isShowModalErrorVisible: Boolean) {
        if (isShowModalErrorVisible) {
            AlertDialog.Builder(this)
                .setTitle("Porra Morty")
                .setMessage("Você ta sem internet, não fode")
                .setPositiveButton("Try again") { dialog: DialogInterface?, which: Int -> myViewModel.onClickTryAgain() }
                .setNegativeButton("Exit") { dialog: DialogInterface?, which: Int ->
                    Toast.makeText(
                        this@PresentationCharacterListActivity,
                        "Porra Morty você fudeo comigo",
                        Toast.LENGTH_SHORT
                    ).show()
                    myViewModel.onClickQuit()
                }
                .setCancelable(false)
                .create()
                .show()
        }
    }

    override fun notify(action: PresentationCharacterListAction?) {
        if (action is GoToInfo) {
            val character = action.character
            startInfo(character)
        } else if (action is Finish) {
            finish()
        }
    }

    private fun startInfo(character: Character) {
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra("c", character)
        startActivity(intent)
    }
}

