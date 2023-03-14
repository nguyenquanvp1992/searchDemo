package com.quannv.searchapplication.repository

import com.quannv.searchapplication.client.SearchAPIClient
import com.quannv.searchapplication.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: SearchAPIClient
){

    private val _searchResults = BehaviorSubject.create<SearchResponse>()
    val searchResults: Observable<SearchResponse> = _searchResults.hide()

    suspend fun search(keySearch: String, page: Int, perPage: Int) {
        val result = api.search(
            header = "Iuv7rLajnL5ru8sSoN8ztI2bCadewP2E0b7XQa9vIde0L2aT7vDnPip2",
            query = keySearch,
            page = page,
            perPage = perPage
        ).execute()
    }
}