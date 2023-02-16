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
    init {
        getAllSchoolData()
    }
    fun getAllSchoolData() {
        viewModelScope.launch(Dispatchers.IO) {
            nycSchools.postValue(
                async {
                    MyRetrofit.getService().getSchools().body()
                }.await()
            )
        }
    }

    fun getSchoolsCount() : Int {
        return nycSchools.value?.size ?: 0
    }
}