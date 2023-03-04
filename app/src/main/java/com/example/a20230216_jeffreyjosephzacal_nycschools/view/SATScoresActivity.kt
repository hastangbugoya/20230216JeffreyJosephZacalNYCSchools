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

        val myViewModel: SATScoresViewModel by viewModels()
        myViewModel.nycSATScores.observe(this) {
            intent.getStringExtra("school_id")?.let { dbn ->
                myViewModel.getSchoolScores(dbn)?.let { scores ->
                    binding.apply {
                        schoolName.apply {
                            text = scores.schoolName
                            contentDescription = "SAT Scores for ${scores.schoolName}}"
                        }
                        numTakers.apply {
                            text = scores.numOfSatTestTakers
                            contentDescription = "$text students took S.A.T.s"
                        }
                        criticalReadingScore.apply {
                            text = scores.satCriticalReadingAvgScore
                            contentDescription = "average critical reading score is $text"
                        }
                        mathAvgScore.apply {
                            text = scores.satMathAvgScore
                            contentDescription = "average math score is $text"
                        }
                        writingAvgScore.apply {
                            text = scores.satWritingAvgScore
                            contentDescription = "average writing score is $text"
                        }
                    }
                } ?: Toast.makeText(this, "School ID not found id data", Toast.LENGTH_LONG).show()

            } ?: Toast.makeText(this, "Error on retrieving school ID", Toast.LENGTH_LONG).show()
        }
    }
}