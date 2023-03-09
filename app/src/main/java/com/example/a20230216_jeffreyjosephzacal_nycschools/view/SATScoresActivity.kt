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
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.AlertType
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivitySatscoresBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.SATScoresViewModel
import com.google.android.material.snackbar.Snackbar

class SATScoresActivity : AppCompatActivity() , SATScoresViewModel.UIUpdate{
    val binding : ActivitySatscoresBinding by lazy {
        ActivitySatscoresBinding.inflate(LayoutInflater.from(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)

        val myViewModel: SATScoresViewModel by viewModels()
        myViewModel.setImplementingUI(this)
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
                } ?: showViewModelAlert("School ID not found in SAT scores data")

            } ?: showViewModelAlert("Error receiving school ID")
        }
    }

    override fun showViewModelAlert(m: String) {
        Snackbar.make(binding.root, m, 5000)
            .setBackgroundTint(getColor(AlertType.ERROR.bgColor))
            .setTextColor(getColor(AlertType.ERROR.fgColor))
            .show()
    }

}