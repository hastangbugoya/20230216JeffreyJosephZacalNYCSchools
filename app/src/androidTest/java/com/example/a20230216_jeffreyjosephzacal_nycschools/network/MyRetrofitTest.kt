package com.example.a20230216_jeffreyjosephzacal_nycschools.network

import com.example.a20230216_jeffreyjosephzacal_nycschools.data.NYSchools
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.NYSchoolsItem
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.SATScores
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class MyRetrofitTest {
    @Test
    fun testRetrofitInstance() {
        //Get an instance of Retrofit
        val instance = MyRetrofit.createRetofit()
        //Assert that, Retrofit's base url matches to our BASE_URL
        //baseurl should be placed in Buildconfig for different APIs for different builds
        assert(instance.baseUrl().url().toString() == "https://data.cityofnewyork.us/resource/")
    }

    @Test
    fun TestEndpoints() {
        val service = MyRetrofit.getService()
        var response: Response<NYSchools>
        runBlocking {
            response = service.getSchools()
        }
        assertNotNull(response.body())
        assertNull(response.errorBody())
        assertEquals(response.code(), 200)
        var response2: Response<SATScores>
        runBlocking {
            response2 = service.getSATScores()
        }
        assertNotNull(response2.body())
        assertNull(response2.errorBody())
        assertEquals(response2.code(), 200)
    }
}