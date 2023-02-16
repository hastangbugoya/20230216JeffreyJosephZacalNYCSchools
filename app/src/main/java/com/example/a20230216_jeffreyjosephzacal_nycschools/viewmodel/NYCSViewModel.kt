package com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel

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
    var currentSchool = MutableLiveData<String?>().apply { value = null }

    fun getAllSchool() {
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

}