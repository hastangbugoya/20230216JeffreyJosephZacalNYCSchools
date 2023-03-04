package com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel
/*
 * ViewModel using MutableLiveData observed in MainActivity
 *
 */

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.BiometricStatus
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.NYSchoolsItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.network.MyRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NYCSViewModel : ViewModel() {
    var nycSchools = MutableLiveData<List<NYSchoolsItem>>()
    var biometricStatus = MutableLiveData<BiometricStatus?>().apply { value = BiometricStatus.BIOMETRIC_UNKNOWN }

    init {
        getAllSchoolData()
    }
    fun getAllSchoolData() {
        viewModelScope.launch(Dispatchers.Main) {
            nycSchools.value = withContext(Dispatchers.IO) {
                MyRetrofit.getService().getSchools().body()
            }
        }


//        viewModelScope.launch(Dispatchers.IO) {
//            nycSchools.postValue(
//                async {
//                    MyRetrofit.getService().getSchools().body()
//                }.await()
//            )
//        }
    }

    fun getSchoolsCount() : Int {
        return nycSchools.value?.size ?: 0
    }
}