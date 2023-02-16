package com.example.a20230216_jeffreyjosephzacal_nycschools

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivityMainBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.view.NYSchoolListAdapter
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.NYCSViewModel

class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var myAdapter: NYSchoolListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val component = DaggerSchoolsAdapterComponent
//            .builder().contextModule(ContextModule(this)).build()
//        component.inject(this)

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)

        var myAdapter = NYSchoolListAdapter(this)
        val mySchoolRecyclerView = binding.schoolList
        mySchoolRecyclerView.adapter = myAdapter
        val sharedViewModel = ViewModelProvider(this).get(NYCSViewModel::class.java)
        sharedViewModel.nycSchools.observe(this) {
            myAdapter.setSchools(it)
        }

        sharedViewModel.nycSATScores.observe(this) {

        }
    }

}

