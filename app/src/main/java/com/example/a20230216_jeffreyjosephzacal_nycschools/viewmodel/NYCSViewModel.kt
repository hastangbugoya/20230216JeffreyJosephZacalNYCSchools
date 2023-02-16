package com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.NYSchoolsItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.SATScoresItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.network.MyRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NYCSViewModel : ViewModel() {
    var nycSchools = MutableLiveData<List<NYSchoolsItem>>()
    var nycSATScores = MutableLiveData<List<SATScoresItem>>()
    init {
        getAllSchoolData()
    }
    fun getAllSchoolData() {
        viewModelScope.launch(Dispatchers.IO) {
            nycSchools.postValue(
               async {
                    MyRetrofit.create().getSchools().body()
                }.await())
            nycSATScores.postValue(
                async{
                    MyRetrofit.create().getSATScores().body()
                }.await())
            }
    }

    fun getSATScores(dbn : String) : SATScoresItem? {
        Log.d("Meow", "Looking for " + dbn)
        Log.d("Meow", "from list count " + nycSATScores.value?.size.toString())
        return nycSATScores.value?.firstOrNull { it.dbn.equals(dbn,true) }
    }

    fun getSchoolsCount() : Int {
        return nycSchools.value?.size ?: 0
    }

    fun getSATScoresCount() : Int {
        return nycSATScores.value?.size ?: 0
    }
}