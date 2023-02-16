package com.example.a20230216_jeffreyjosephzacal_nycschools.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.NYSchoolsItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.SchoolItemBinding


class NYSchoolListAdapter(private val context: Context) : RecyclerView.Adapter<NYSchoolListAdapter.SchoolViewHolder>() {
    var schoolList : MutableList<NYSchoolsItem> = mutableListOf()
    inner class SchoolViewHolder(binding: SchoolItemBinding ) : RecyclerView.ViewHolder(binding.root) {
        var binding : SchoolItemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding = SchoolItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.binding.apply {
            schoolName.text = schoolList.get(position).schoolName
            schoolAddress.text = schoolList.get(position).primaryAddressLine1
            schoolEmail.text = schoolList.get(position).schoolEmail
            schoolItem.setOnClickListener() {
                Intent(context, SATScoresActivity::class.java).apply {
                    putExtra("data", Bundle().apply {
                        putString("dbn", schoolList.get(position).dbn)
                    })
                    startActivity(context,this,null)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return  schoolList.size
    }

    fun setSchools(list : List<NYSchoolsItem>) {
        schoolList = list.toMutableList()
        refreshView()
    }

    fun refreshView() {
        notifyDataSetChanged()
    }
}