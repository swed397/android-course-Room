package com.android.course.room.present.film_info

import com.android.course.room.data.repo.FilmsDbRepo
import com.android.course.room.present.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmInfoFragmentViewModel @Inject constructor(private val filmsDbRepo: FilmsDbRepo) :
    BaseViewModel(filmsDbRepo) {

    private val _filmPreviewStateFlow =
        MutableStateFlow<FilmInfoFragmentUiState>(FilmInfoFragmentUiState.OnLoading)
    val stateFlow: Flow<FilmInfoFragmentUiState>
        get() = _filmPreviewStateFlow

    fun onRefresh(filmId: Long) {
        scopeMain.launch {
            try {
                _filmPreviewStateFlow.emit(FilmInfoFragmentUiState.OnLoading)
                val deferredResultList = filmsDbRepo.getFilmInfoById(filmId)
                _filmPreviewStateFlow.emit(FilmInfoFragmentUiState.OnSuccess(deferredResultList.await()))
            } catch (e: RuntimeException) {
                _filmPreviewStateFlow.emit(FilmInfoFragmentUiState.OnError(e))
            }
        }
    }
}