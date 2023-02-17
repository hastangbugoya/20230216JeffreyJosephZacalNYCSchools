package com.example.a20230216_jeffreyjosephzacal_nycschools.view
/*
 * Displays school SAT scores
 * Other details can be added as required such a maps, url links and other school details
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivitySatscoresBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.SATScoresViewModel

class SATScoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySatscoresBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)

        val myViewModel: SATScoresViewModel by viewModels<SATScoresViewModel>()
        myViewModel.nycSATScores.observe(this) {
            intent.getStringExtra("school_id")?.let { dbn ->
                myViewModel.getSchoolScores(dbn)?.let { scores ->
                    binding.apply {
                        schoolName.text = scores.schoolName
                        numTakers.text = scores.numOfSatTestTakers
                        criticalReadingScore.text = scores.satCriticalReadingAvgScore
                        mathAvgScore.text = scores.satMathAvgScore
                        writingAvgScore.text = scores.satWritingAvgScore
                    }
                } ?: Toast.makeText(this, "School ID not found id data", Toast.LENGTH_LONG).show()
            } ?: Toast.makeText(this, "Error on retrieving school ID", Toast.LENGTH_LONG).show()
        }
    }
}