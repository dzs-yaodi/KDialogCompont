package com.kotlinmvp.api

import com.maturedating.datingapp.other.myapplication.api.ResultBase
import retrofit2.http.GET
import java.util.*
import io.reactivex.Observable

interface ApiService{

    @GET("api/coin/hotsearch")
    fun getHotsearch(): Observable<ResultBase<List<String>>>

}