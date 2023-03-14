package com.quannv.searchapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quannv.searchapplication.base.BaseViewModel
import com.quannv.searchapplication.event.SingleLiveEvent
import com.quannv.searchapplication.repository.SearchRepository
import com.quannv.searchapplication.response.SearchResponse
import com.quannv.searchapplication.util.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: SearchRepository
) : BaseViewModel() {

    private val _searchResult = SingleLiveEvent<SearchResponse>()
    val searchResult: LiveData<SearchResponse> = _searchResult
    val isShowLoading = SingleLiveEvent<Boolean>()

    init {
        disposables.addAll(
            repository.searchResults.subscribe {
                _searchResult.postValue(it)
                isShowLoading.postValue(false)
            }
        )
    }

    fun search(keySearch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.search(keySearch, 1, Const.PER_PAGE)
        }
    }

    fun nextPage(currentKey: String, nextPage: Long) {
        if (isShowLoading.value == true) return
        isShowLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.search(currentKey, nextPage, Const.PER_PAGE)
            } catch (e: Exception) {
                isShowLoading.postValue(false)
            }
        }
    }
}