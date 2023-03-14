package com.quannv.searchapplication.viewModel

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

    val searchResult = MutableLiveData<SearchResponse>()
    val isShowLoading = SingleLiveEvent<Boolean>()
    var currentKeySearch: String = ""

    init {
        disposables.addAll(
            repository.searchResults.subscribe {
                searchResult.postValue(it)
                isShowLoading.postValue(false)
            }
        )
    }

    fun search(keySearch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.search(keySearch, 1, Const.PER_PAGE)
            currentKeySearch = keySearch
        }
    }

    fun nextPage(nextPage: Long) {
        if (isShowLoading.value == true) return
        isShowLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.search(currentKeySearch, nextPage, Const.PER_PAGE)
            } catch (e: Exception) {
                isShowLoading.postValue(false)
            }
        }
    }
}