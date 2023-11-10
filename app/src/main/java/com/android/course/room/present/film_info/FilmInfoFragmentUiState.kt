package com.android.course.room.present.film_info

import com.android.course.room.domain.FilmInfo

sealed interface FilmInfoFragmentUiState {
    data object OnLoading : FilmInfoFragmentUiState
    data class OnSuccess(val data: FilmInfo) : FilmInfoFragmentUiState
    data class OnError(val e: Exception) : FilmInfoFragmentUiState
}