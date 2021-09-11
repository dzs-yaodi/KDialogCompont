package com.kotlinmvp.api

import com.afftechlimited.adultdatingapp.other.myapplication.api.ResultBase
import retrofit2.http.GET
import io.reactivex.Observable

interface ApiService{

    @GET("api/coin/hotsearch")
    fun getHotsearch(): Observable<ResultBase<List<String>>>

}