package com.android.course.room.present.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.android.course.room.App
import com.android.course.room.R
import com.android.course.room.databinding.FilmAddFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFilmFragment : Fragment(R.layout.film_add_fragment) {

    private lateinit var binding: FilmAddFragmentBinding

    private val viewModel: AddFilmFragmentViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val scopeMain = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var selectedGenre: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireContext().applicationContext as App).appComponent.injectAddFilmFragment(this)
        binding = FilmAddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dropDown = binding.dropDown

        scopeMain.launch {
            val genresList = viewModel.getAllGenres()

            dropDown.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                genresList
            )

            dropDown.onItemSelectedListener =
                DropDownGenresOnItemSelectedListener(genresList) { genre ->
                    selectedGenre = genre
                }
        }

        binding.saveButton.setOnClickListener {
            Log.d("TEST", selectedGenre.toString())
        }
    }

    companion object {
        fun newInstance(): AddFilmFragment = AddFilmFragment()
    }
}