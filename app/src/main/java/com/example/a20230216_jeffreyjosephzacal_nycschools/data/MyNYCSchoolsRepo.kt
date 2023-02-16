package com.example.a20230216_jeffreyjosephzacal_nycschools.data

import android.util.Log
import com.example.a20230216_jeffreyjosephzacal_nycschools.network.MyRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyNYCSchoolsRepo {
    fun getAllSchool(): List<NYSchoolsItem> {
        var data = listOf<NYSchoolsItem>()
        CoroutineScope(Dispatchers.IO).launch {
            data = async { MyRetrofit.getService().getSchools().body()?.toList() ?: listOf() }.await()
        }
        return data
    }

    fun getAllScores() : List<SATScoresItem> {
        var data = listOf<SATScoresItem>()
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            data = async { MyRetrofit.getService().getSATScores().body()?.toList() ?: listOf() }.await()
        }
        return data
    }
}