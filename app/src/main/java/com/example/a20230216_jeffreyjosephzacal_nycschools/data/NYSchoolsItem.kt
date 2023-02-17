package com.example.a20230216_jeffreyjosephzacal_nycschools.data
/*
 * Response items
 */

import com.google.gson.annotations.SerializedName

data class NYSchoolsItem(
    @SerializedName("dbn")
    var dbn: String? = "",
    @SerializedName("primary_address_line_1")
    var primaryAddressLine1: String? = "",
    @SerializedName("school_email")
    var schoolEmail: String? = "",
    @SerializedName("school_name")
    var schoolName: String? = "",
    @SerializedName("total_students")
    var totalStudents: String? = "",
    @SerializedName("website")
    var website: String? = "",
    @SerializedName("zip")
    var zip: String? = ""
)