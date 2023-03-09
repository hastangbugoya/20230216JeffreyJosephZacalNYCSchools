package com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel
/*
 * ViewModel using MutableLiveData observed in MainActivity
 *
 */

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.AuthenticationResult
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.BiometricStatus
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.NYSchoolsItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.network.MyRetrofit
import kotlinx.coroutines.*

class NYCSViewModel : ViewModel() {
    var nycSchools = MutableLiveData<List<NYSchoolsItem>>()
    lateinit var ui : UIUpdate
    var biometricStatus: BiometricStatus? = null
        get() = field
        set(status) {
            field = status
        }
    var authenticationResult: AuthenticationResult? = null
        get() = field
        set(result) {
            field = result
        }
    init {
        getAllSchoolData()
    }

    fun getAllSchoolData() {
//        viewModelScope.launch(Dispatchers.Main) {
//            nycSchools.value = withContext(Dispatchers.IO) {
//                MyRetrofit.getService().getSchools().body()
//            }
//        }
        try {
            viewModelScope.launch(Dispatchers.IO) {
                nycSchools.postValue(
                    async {
                        MyRetrofit.getService().getSchools().body()
                    }.await()
                )
            }
        } catch (e: Exception) {
            // Handle e
            ui.showViewModelAlert("Exception : $e")
            nycSchools.value = listOf()
        }
    }

    fun getSchoolsCount(): Int {
        return nycSchools.value?.size ?: 0
    }

    interface UIUpdate {
        fun showViewModelAlert(m : String)
    }

    fun setImplementingUI(uiupdate : UIUpdate) {
        ui = uiupdate
    }
}