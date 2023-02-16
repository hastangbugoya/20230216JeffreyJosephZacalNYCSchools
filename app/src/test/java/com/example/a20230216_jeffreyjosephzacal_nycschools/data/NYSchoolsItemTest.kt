package com.example.a20230216_jeffreyjosephzacal_nycschools.data

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class NYSchoolsItemTest {
    lateinit var school : NYSchoolsItem
    @Before
    fun setUp() {
        school = NYSchoolsItem(
            "DBN123",
            "123 Main Street",
            "school@example.com",
            "Example School",
            "100",
            "Yes",
            "http://www.example.com",
            "12345"
        )
    }

    @Test
    fun getDBN() {
        assertEquals("DBN123", school.dbn)
        assertEquals("123 Main Street", school.primaryAddressLine1)
        assertEquals("school@example.com", school.schoolEmail)
        assertEquals("Example School", school.schoolName)
        assertEquals("100", school.totalStudents)
        assertEquals("Yes", school.transfer)
        assertEquals("http://www.example.com", school.website)
        assertEquals("12345", school.zip)
    }
}