package com.subranil.pagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.subranil.pagination.data.GithubRepository

class ViewModelFactory(private val repository: GithubRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRepositoriesViewModel::class.java)) {
            return SearchRepositoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}