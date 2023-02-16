package com.example.a20230216_jeffreyjosephzacal_nycschools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivitySatscoresBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.NYCSViewModel

class SATScoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySatscoresBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        val sharedViewModel = ViewModelProvider(this).get(NYCSViewModel::class.java)
        Log.d("Meow", "SATScoresActivity viewModel SAT Scores Count : " + sharedViewModel.nycSATScores.value?.size.toString())
        intent.getStringExtra("school_id")?.let { dbn ->
            sharedViewModel.getSATScores(dbn)?.let { scores ->
                binding.numTakers.text = scores.numOfSatTestTakers
                binding.criticalReadingScore.text = scores.satCriticalReadingAvgScore
                binding.mathAvgScore.text = scores.satMathAvgScore
                binding.writingAvgScore.text = scores.satWritingAvgScore
            } ?: Toast.makeText(this, "School ID not found", Toast.LENGTH_LONG).show()
        } ?: Toast.makeText(this, "No data in intent", Toast.LENGTH_LONG).show()
    }
}