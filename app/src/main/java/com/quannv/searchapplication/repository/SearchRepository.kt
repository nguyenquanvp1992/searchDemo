package com.quannv.searchapplication.repository

import android.util.Log
import com.google.gson.Gson
import com.quannv.searchapplication.client.SearchAPIClient
import com.quannv.searchapplication.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: SearchAPIClient
){

    private val _searchResults = BehaviorSubject.create<SearchResponse>()
    val searchResults: Observable<SearchResponse> = _searchResults.hide()

    suspend fun search(keySearch: String, page: Long, perPage: Int) {
        withContext(Dispatchers.IO) {
            val result = api.search(
                header = "Iuv7rLajnL5ru8sSoN8ztI2bCadewP2E0b7XQa9vIde0L2aT7vDnPip2",
                query = keySearch,
                page = page,
                perPage = perPage
            ).execute()
            result.body()?.let {
                _searchResults.onNext(it)
            } ?: kotlin.run {
                Log.d("NguyenQuan", Gson().toJson(result))
            }
        }
    }
}