package com.example.rickmortymvvm.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortymvvm.databinding.ActivityMainBinding
import com.example.rickmortymvvm.presentation.PresentationCharacterListAction
import com.example.rickmortymvvm.presentation.PresentationCharacterListAction.Finish
import com.example.rickmortymvvm.presentation.PresentationCharacterListAction.GoToInfo
import com.example.rickmortymvvm.presentation.PresentationCharacterListState
import com.example.rickmortymvvm.presentation.PresentationCharacterListViewModelImpl
import com.example.rickmortymvvm.presentation.models.CharacterVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class PresentationCharacterListActivity : AppCompatActivity(), OnClickCharacter {

    private val characterListAdapter = ListCharacterAdapter(this)
    private val myPresentationCharacterListViewModel: PresentationCharacterListViewModelImpl by viewModel()
    private lateinit var pbLoading: ProgressBar
    private lateinit var rvCharacter: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvCharacter = binding.rvCharacter
        rvCharacter.adapter = characterListAdapter

        rvCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    myPresentationCharacterListViewModel.onScrollFinal()
                }
            }
        })

        pbLoading = binding.mainPbLoading

        myPresentationCharacterListViewModel.setUp()

        myPresentationCharacterListViewModel.state.observe(this) { NewStatus ->
            notify(NewStatus)
        }
        myPresentationCharacterListViewModel.action.observe(this) { NewAction ->
            notify(NewAction)
        }
    }

    override fun onClickCharacter(characterVM: CharacterVM) {
        myPresentationCharacterListViewModel.onClickCharacter(characterVM)
    }

    private fun notify(state: PresentationCharacterListState) {
        setupLoading(state.isLoadingVisible)
        setCharacterList(state.listCharacterVM)
        setupListCharacter(state.isListCharacterVisible)
        setupModalError(state.isShowModalErrorVisible)
    }

    private fun setupLoading(isLoadingVisible: Boolean) {
        pbLoading.isVisible = isLoadingVisible
    }

    private fun setCharacterList(characterVMList: List<CharacterVM>) {
        characterListAdapter.setListAdapter(characterVMList)
    }

    private fun setupListCharacter(isListCharacterVisible: Boolean) {
        rvCharacter.isVisible = isListCharacterVisible
    }

    private fun setupModalError(isShowModalErrorVisible: Boolean) {
        if (isShowModalErrorVisible) {
            AlertDialog.Builder(this)
                .setTitle("Connection problem")
                .setMessage("You don't have internet")
                .setPositiveButton("Try again") { dialog: DialogInterface?, which: Int -> myPresentationCharacterListViewModel.onClickTryAgain() }
                .setNegativeButton("Exit") { dialog: DialogInterface?, which: Int ->
                    myPresentationCharacterListViewModel.onClickQuit()
                }
                .setCancelable(false)
                .create()
                .show()
        }
    }

    private fun notify(action: PresentationCharacterListAction) {
        when (action) {
            Finish -> finish()
            is GoToInfo -> {
                val character = action.characterVM
                startInfo(character)
            }
        }
    }

    private fun startInfo(characterVM: CharacterVM) {
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra("c", characterVM)
        startActivity(intent)
    }
}
