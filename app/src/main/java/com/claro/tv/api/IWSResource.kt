package com.claro.tv.api

import com.claro.tv.model.pojo.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWSResource {
    // @GET("/schedule?country=US&date=2020-02-25")
    @GET("/schedule ")
    fun getShowTVbyDate(@Query("country") country: String, @Query("date") date: String): Call<List<ShowTV>>

    //@GET("/search/shows?q=girls")
    @GET("/search/shows")
    fun getShowTVbyQuery(@Query("q") query: String): Call<List<ShowTVQuery>>

    //@GET("/shows/1")
    @GET("/shows/{key}")
    fun getShowTVbyKey(@Path("key") key: String): Call<ShowDetails>

    //@GET("/shows/1/cast")
    @GET("/shows/{key}/cast")
    fun getShowTVAndCastByKey(@Path("key") key: String): Call<List<Cast>>
}