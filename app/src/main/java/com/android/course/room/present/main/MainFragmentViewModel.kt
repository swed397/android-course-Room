package com.android.course.room.present.main

import com.android.course.room.data.repo.FilmsDbRepo
import com.android.course.room.present.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val filmsDbRepo: FilmsDbRepo) :
    BaseViewModel(filmsDbRepo) {

    private val _filmPreviewStateFlow =
        MutableStateFlow<MainFragmentUiState>(MainFragmentUiState.OnLoading)
    val stateFlow: Flow<MainFragmentUiState>
        get() = _filmPreviewStateFlow

    fun onRefresh() {
        scopeIO.launch {
            try {
                _filmPreviewStateFlow.emit(MainFragmentUiState.OnLoading)
                val deferredResultList = filmsDbRepo.getFilmsPreviewList()
                _filmPreviewStateFlow.emit(MainFragmentUiState.OnSuccess(deferredResultList))
            } catch (e: RuntimeException) {
                _filmPreviewStateFlow.emit(MainFragmentUiState.OnError(e))
            }
        }
    }
}