package com.subranil.pagination

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.subranil.pagination.api.GithubService
import com.subranil.pagination.data.GithubRepository
import com.subranil.pagination.db.RepoDatabase
import com.subranil.pagination.viewmodel.ViewModelFactory

object Injection {
    private fun provideRepository(context: Context): GithubRepository {
        return GithubRepository(GithubService.create(), RepoDatabase.getInstance(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository(context))
    }
}