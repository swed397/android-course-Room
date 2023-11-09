package com.android.course.room.present.main

import com.android.course.room.domain.PreviewFilmInfo
import java.lang.Exception

sealed interface MainFragmentUiState {
    data object OnLoading : MainFragmentUiState
    data class OnSuccess(val data: List<PreviewFilmInfo>) : MainFragmentUiState
    data class OnError(val e: Exception) : MainFragmentUiState
}