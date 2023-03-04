package com.example.a20230216_jeffreyjosephzacal_nycschools
/*
 * MainActivity for app
 * di is used for recycler view adapted
 *
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.AlertType
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.AuthenticationResult
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.BiometricStatus
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivityMainBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.di.ContextModule
import com.example.a20230216_jeffreyjosephzacal_nycschools.di.DaggerSchoolsAdapterComponent
import com.example.a20230216_jeffreyjosephzacal_nycschools.di.NYSchoolListAdapterModule
import com.example.a20230216_jeffreyjosephzacal_nycschools.security.AuthenticateFingerprint
import com.example.a20230216_jeffreyjosephzacal_nycschools.view.NYSchoolListAdapter
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.NYCSViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AuthenticateFingerprint.UIUpdate {

    @Inject
    lateinit var myAdapter: NYSchoolListAdapter

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    val myViewModel: NYCSViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSchoolsAdapterComponent.builder()
            .nYSchoolListAdapterModule(NYSchoolListAdapterModule())
            .contextModule(ContextModule(this)).build().inject(this)

        val view = binding.root
        setContentView(view)

        val mySchoolRecyclerView = binding.schoolList
        mySchoolRecyclerView.adapter = myAdapter

        myViewModel.nycSchools.observe(this) {
            myAdapter.setSchools(it)
        }
    }

    override fun displayAlert(s: String, type: AlertType) {
        Snackbar.make(binding.schoolList, s, 5000)
            .setBackgroundTint(getColor(type.bgColor))
            .setTextColor(getColor(type.fgColor))
            .show()
    }

    override fun updateAAuthenticationStatus(status: BiometricStatus) {
        myViewModel.biometricStatus.value = status
        when (status) {
            BiometricStatus.BIOMETRIC_UNKNOWN -> {}
            BiometricStatus.BIOMETRIC_SUCCESS -> {}
            BiometricStatus.BIOMETRIC_ERROR_NO_HARDWARE -> {}
            BiometricStatus.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {}
            BiometricStatus.BIOMETRIC_ERROR_NONE_ENROLLED -> {}
        }
    }

    override fun displayAuthenticationResult(result: AuthenticationResult) {
        val alertType : AlertType
        alertType = when(result) {
            AuthenticationResult.AuthenticationSucceeded -> {
                AlertType.DEFAULT
            }
            else -> {
                AlertType.ERROR
            }
        }
        Snackbar.make(binding.schoolList, result.errString, 5000)
            .setBackgroundTint(getColor(alertType.bgColor))
            .setTextColor(getColor(alertType.bgColor))
            .show()
    }
}

