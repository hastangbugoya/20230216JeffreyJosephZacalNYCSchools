package com.example.a20230216_jeffreyjosephzacal_nycschools.data

import com.example.a20230216_jeffreyjosephzacal_nycschools.R
/**
 * Alert types and snackbar colors
 * @param bgColor resource id for background color
 * @param fgColor resource id for text color
 */
enum class AlertType(val bgColor: Int, val fgColor: Int) {
    DEFAULT(R.color.green_opaque, R.color.white),
    ERROR(R.color.red_opaque, R.color.white)
}