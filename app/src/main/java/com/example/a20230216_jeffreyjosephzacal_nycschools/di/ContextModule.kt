package com.example.a20230216_jeffreyjosephzacal_nycschools.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val thiscontext: Context) {
    @Provides
    fun provideContext(): Context = thiscontext
}