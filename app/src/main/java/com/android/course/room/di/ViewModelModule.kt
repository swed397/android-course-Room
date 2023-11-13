package com.android.course.room.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.course.room.present.add.AddFilmFragmentViewModel
import com.android.course.room.present.info.FilmInfoFragmentViewModel
import com.android.course.room.present.main.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun mainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilmInfoFragmentViewModel::class)
    abstract fun filmInfoFragmentViewModel(viewModel: FilmInfoFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddFilmFragmentViewModel::class)
    abstract fun addNewFilmFragmentViewModel(viewModel: AddFilmFragmentViewModel): ViewModel
}