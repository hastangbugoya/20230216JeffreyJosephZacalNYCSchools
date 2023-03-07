package com.example.a20230216_jeffreyjosephzacal_nycschools.view
/*
 * Adapter for MainActivity recycler view
 * MainActivty context is injected to launch second activity
 */
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.NYSchoolsItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.databinding.SchoolItemBinding
import javax.inject.Inject

class NYSchoolListAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<NYSchoolListAdapter.SchoolViewHolder>() {
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
            schoolName.text = StringBuilder()
                .append(schoolList[position].schoolName)
                .append(" (")
                .append(schoolList[position].dbn).append(")")
            val add = schoolList[position].primaryAddressLine1
            if (!add.isNullOrEmpty()) {
                schoolAddress.apply {
                    text = add
                    visibility = View.VISIBLE
                }
                directionsIcon.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        openMapsForDirections("$add ${schoolList[position].city ?: ""} ${schoolList[position].zip ?: ""}", context)
                    }
                }
            } else {
                directionsIcon.visibility = View.GONE
                schoolAddress.visibility = View.GONE
            }
            val email = schoolList[position].schoolEmail
            if(!email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                schoolEmail.text = email
                emailIcon.setOnClickListener {
                    openEmailApp(email,context.packageManager,context)
                    it.visibility = View.VISIBLE
                }
                schoolEmail.visibility = View.VISIBLE
            } else {
                    schoolEmail.visibility = View.GONE
                    emailIcon.visibility = View.GONE
            }
            schoolItem.setOnClickListener {
                Intent(context, SATScoresActivity::class.java).apply {
                    putExtra("school_id", schoolList.get(position).dbn)
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

    fun openEmailApp(text: String, packageManager: PackageManager, context: Context) {
        val sendIntent = Intent().apply {
            action = ACTION_SENDTO
            putExtra(EXTRA_EMAIL, arrayOf(text))
            data = Uri.parse("mailto:")
            addFlags(FLAG_ACTIVITY_NEW_DOCUMENT)
        }
        val shareIntent = createChooser(sendIntent, "Send an email")

        // Verify that there are apps available to handle the intent
        if (sendIntent.resolveActivity(packageManager) != null) {
            context.startActivity(shareIntent)
        }
    }

    fun openMapsForDirections(address: String, context: Context) {
        // Create a URI for the address
        val uri = Uri.parse("google.navigation:q=$address")
        // Create an intent with the URI
        val intent = Intent(Intent.ACTION_VIEW, uri).addFlags(FLAG_ACTIVITY_NEW_DOCUMENT)
        // Set the package name for the Google Maps app to ensure that it is used to handle the intent
        intent.setPackage("com.google.android.apps.maps")
        // Start google map with the intent
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            // No maps - Create a chooser intent to allow the user to select a secondary app
            val chooserIntent = createChooser(intent, "Open with")
            // Add the secondary app to the chooser intent
            val backupIntent = Intent(Intent.ACTION_VIEW)
            backupIntent.data = Uri.parse("geo:0,0?q=$address")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(backupIntent))
            if (chooserIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(chooserIntent)
            } else {
                Toast.makeText(context, "No apps found to handle directions", Toast.LENGTH_SHORT).show()
            }
        }
    }
}