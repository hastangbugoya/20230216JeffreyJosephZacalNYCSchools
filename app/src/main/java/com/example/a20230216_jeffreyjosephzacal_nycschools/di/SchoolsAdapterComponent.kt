package com.example.a20230216_jeffreyjosephzacal_nycschools.di

import android.content.Context
import com.example.a20230216_jeffreyjosephzacal_nycschools.MainActivity
import dagger.Component

@Component(modules = [ContextModule::class, NYSchoolListAdapterModule::class])
interface SchoolsAdapterComponent {
    fun inject(activity: MainActivity)
}