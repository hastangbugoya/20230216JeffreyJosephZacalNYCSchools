package com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel
/*
 * ViewModel using MutableLiveData observed in SATScoresActivity
 *
 */
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.SATScoresItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.network.MyRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SATScoresViewModel : ViewModel() {
    var nycSATScores = MutableLiveData<List<SATScoresItem>>()
    lateinit var ui : UIUpdate
    init {
        getAllSATData()
    }
    fun getAllSATData() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                nycSATScores.postValue(
                    async{
                        MyRetrofit.getService().getSATScores().body()
                    }.await())
            }
        } catch (e: Exception) {
            ui.showViewModelAlert("Exception : $e")
            nycSATScores.value = listOf()
        }
    }
    fun getSchoolScores(sID : String) : SATScoresItem? = nycSATScores.value?.firstOrNull { it.dbn.equals(sID) }
    fun scoresCount() : Int = nycSATScores.value?.size ?: 0


    interface UIUpdate {
        fun showViewModelAlert(m : String)
    }

    fun setImplementingUI(uiupdate : UIUpdate) {
        ui = uiupdate
    }
}