package com.example.a20230216_jeffreyjosephzacal_nycschools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivityMainBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivitySatscoresBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.NYCSViewModel

class SATScoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySatscoresBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        val myViewModel : NYCSViewModel by viewModels()

        val schoolScores = myViewModel.nycSATScores.value?.firstOrNull({
            it.dbn.equals(myViewModel.currentSchool.value)
        })
    }
}