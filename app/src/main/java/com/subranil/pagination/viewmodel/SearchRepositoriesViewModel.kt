package com.subranil.pagination.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.subranil.pagination.data.GithubRepository
import com.subranil.pagination.model.Repo

class SearchRepositoriesViewModel(private val repository: GithubRepository) : ViewModel() {
    private val _searchList = MutableLiveData<PagingData<Repo>>()

    @ExperimentalPagingApi
    fun getRepoList(): LiveData<PagingData<Repo>> {
        val response = repository.getSearchResult().cachedIn(viewModelScope)
        _searchList.value = response.value
        return response
    }
}