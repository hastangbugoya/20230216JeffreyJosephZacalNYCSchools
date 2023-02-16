package com.example.a20230216_jeffreyjosephzacal_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivityMainBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.di.ContextModule
import com.example.a20230216_jeffreyjosephzacal_nycschools.view.NYSchoolListAdapter
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.NYCSViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var myAdapter : NYSchoolListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerSchoolsAdapterComponent.builder().contextModule(ContextModule(this)).build()
        component.inject(this)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        val view = binding.root
        setContentView(view)

//        var myAdapter = NYSchoolListAdapter(component)
        val mySchoolRecyclerView = binding.schoolList
        mySchoolRecyclerView.adapter = myAdapter
        val myViewModel : NYCSViewModel by viewModels<NYCSViewModel>()
        myViewModel.nycSchools.observe(this) {
            myAdapter.setSchools(it)
        }
    }
}

