package com.android.course.room.present.film_info

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.android.course.room.App
import com.android.course.room.FILM_INFO_KEY
import com.android.course.room.R
import com.android.course.room.databinding.FilmInfoFragmentBinding
import com.android.course.room.domain.FilmInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FilmInfoFragment : Fragment(R.layout.film_info_fragment) {

    private lateinit var binding: FilmInfoFragmentBinding

    private val viewModel: FilmInfoFragmentViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (requireContext().applicationContext as App).appComponent.injectFilmInfoFragment(this)
        binding = FilmInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val filmId = arguments?.getLong(FILM_INFO_KEY)!!
        viewModel.onRefresh(filmId)

        viewModel.stateFlow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { render(it) }
            .launchIn(lifecycleScope)
    }

    private fun render(stateFlow: FilmInfoFragmentUiState) {
        when (stateFlow) {
            is FilmInfoFragmentUiState.OnLoading -> binding.progressBar.isVisible = true
            is FilmInfoFragmentUiState.OnError -> AlertDialog.Builder(this.requireContext())
                .setTitle("Error")
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()

            is FilmInfoFragmentUiState.OnSuccess -> mapComponents(stateFlow.data)
        }
    }

    private fun mapComponents(data: FilmInfo) {
        binding.progressBar.isGone = true
        binding.filmTitle.text = data.title
        binding.filmAuthors.text = data.authors
        binding.filmInfo.text = data.info
        binding.filmGenre.text = data.genre
        binding.textView4.text = data.rate.toString()
        binding.filmYear.text = data.year.toString()
    }

    companion object {
        fun newInstance(filmId: Long): FilmInfoFragment = FilmInfoFragment().apply {
            this.arguments = bundleOf(Pair(FILM_INFO_KEY, filmId))
        }
    }
}