package com.android.course.room.present.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.android.course.room.App
import com.android.course.room.R
import com.android.course.room.databinding.MainFragmentBinding
import com.android.course.room.present.add.AddFilmFragment
import com.android.course.room.present.info.FilmInfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: MainRecyclerViewAdapter
    private lateinit var progressBar: ProgressBar

    private val viewModel: MainFragmentViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (requireContext().applicationContext as App).appComponent.injectMainFragment(this)
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = binding.mainFragmentProgressBar
        startFilmAddFragment()
        createAdapter()
        viewModel.onRefresh()

        viewModel.stateFlow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { render(it) }
            .launchIn(lifecycleScope)
    }

    private fun render(stateFlow: MainFragmentUiState) {
        when (stateFlow) {
            is MainFragmentUiState.OnLoading -> progressBar.isVisible = true
            is MainFragmentUiState.OnError -> AlertDialog.Builder(this.requireContext())
                .setTitle("Error")
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()

            is MainFragmentUiState.OnSuccess -> {
                progressBar.isGone = true
                adapter.itemList = stateFlow.data
                adapter.setData()
            }
        }
    }

    private fun createAdapter() {
        adapter = MainRecyclerViewAdapter(::startFilmFragment)
        binding.mainRecyclerView.adapter = adapter
    }

    private fun startFilmFragment(id: Long) {
        parentFragmentManager.commit {
            add(R.id.main_fragment_conteiner_view, FilmInfoFragment.newInstance(id))
            addToBackStack(this@MainFragment::class.simpleName)
        }
    }

    private fun startFilmAddFragment() {
        binding.addButton.setOnClickListener {
            parentFragmentManager.commit {
                add(R.id.main_fragment_conteiner_view, AddFilmFragment.newInstance())
                addToBackStack(this@MainFragment::class.simpleName)
            }
        }
    }
}