package com.android.course.room.present.main

import androidx.lifecycle.ViewModel
import com.android.course.room.data.repo.FilmsDbRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val filmsDbRepo: FilmsDbRepo) :
    ViewModel() {

    private val scopeMain = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val _filmPreviewStateFlow =
        MutableStateFlow<MainFragmentUiState>(MainFragmentUiState.OnLoading)
    val stateFlow: Flow<MainFragmentUiState>
        get() = _filmPreviewStateFlow

    fun onRefresh() {
        scopeMain.launch {
            try {
                _filmPreviewStateFlow.emit(MainFragmentUiState.OnLoading)
                val deferredResultList = filmsDbRepo.getFilmsPreviewList()
                _filmPreviewStateFlow.emit(MainFragmentUiState.OnSuccess(deferredResultList.await()))
            } catch (e: RuntimeException) {
                _filmPreviewStateFlow.emit(MainFragmentUiState.OnError(e))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scopeMain.cancel()
        filmsDbRepo.close()
    }
}