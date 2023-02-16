package com.example.a20230216_jeffreyjosephzacal_nycschools.data

import com.example.a20230216_jeffreyjosephzacal_nycschools.network.MyRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyNYCSchoolsRepo {
    fun getAllSchool(scope: CoroutineScope): List<NYSchoolsItem> {
        var data = listOf<NYSchoolsItem>()
        scope.launch(Dispatchers.IO) {
            data = async { MyRetrofit.create().getSchools().body()?.toList() ?: listOf() }.await()
        }
        return data
    }

    fun getAllScores(scope: CoroutineScope) : List<SATScoresItem> {
        var data = listOf<SATScoresItem>()
        scope.launch(Dispatchers.IO) {
            data = async { MyRetrofit.create().getSATScores().body()?.toList() ?: listOf() }.await()
        }
    }
}