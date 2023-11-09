package com.android.course.room.di

import com.android.course.room.present.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RepoModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(mainFragment: MainFragment)
}