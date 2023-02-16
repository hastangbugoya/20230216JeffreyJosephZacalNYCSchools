package com.example.a20230216_jeffreyjosephzacal_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.ActivityMainBinding
import com.example.a20230216_jeffreyjosephzacal_nycschools.view.NYSchoolListAdapter
import com.example.a20230216_jeffreyjosephzacal_nycschools.viewmodel.NYCSViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        val myViewModel : NYCSViewModel by viewModels()
        var myAdapter = NYSchoolListAdapter(this)
        val mySchoolRecyclerView = binding.schoolList
        mySchoolRecyclerView.adapter = myAdapter
        myViewModel.getAllSchool()

        myViewModel.nycSchools.observe(this) {
            myAdapter.setSchools(it)
        }

    }
}