package com.quannv.searchapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quannv.searchapplication.event.SingleLiveEvent
import com.quannv.searchapplication.repository.SearchRepository
import com.quannv.searchapplication.response.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    companion object {
        private const val PER_PAGE = 15
    }

    val searchResult: LiveData<SearchResponse> = SingleLiveEvent<SearchResponse>()

    fun search(keySearch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.search(keySearch, 1, PER_PAGE)
        }
    }

    fun nextPage(currentKey: String, nextPage: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.search(currentKey, nextPage, PER_PAGE)
            } catch (e: Exception) {

            }
        }
    }
}