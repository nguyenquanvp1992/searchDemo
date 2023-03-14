package com.quannv.searchapplication.client

import com.quannv.searchapplication.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchAPIClient {

    @GET("search")
    fun search(
        @Header("Authorization") header: String,
        @Query("query") query: String,
        @Query("page") page: Long,
        @Query("per_page") perPage: Int,
        @Query("orientation") orientation: String? = null,
        @Query("size") size: String? = null,
        @Query("color") color: String? = null,
        @Query("locale") locale: String? = null
    ): Call<SearchResponse>
}