package com.subranil.pagination.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.subranil.pagination.api.GithubService
import com.subranil.pagination.db.RepoDatabase
import com.subranil.pagination.model.Repo

class GithubRepository(private val service: GithubService, private val database: RepoDatabase) {
    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

    @ExperimentalPagingApi
    fun getSearchResult(): LiveData<PagingData<Repo>> {
        val pagingSourceFactory = { database.reposDao().reposByName() }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = GithubRemoteMediator(service, database),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }
}