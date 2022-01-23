package com.subranil.pagination.ui

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import com.subranil.pagination.Injection
import com.subranil.pagination.R
import com.subranil.pagination.databinding.RepoListBinding
import com.subranil.pagination.model.Repo
import com.subranil.pagination.ui.adapter.ReposAdapter
import com.subranil.pagination.ui.adapter.ReposLoadStateAdapter
import com.subranil.pagination.viewmodel.SearchRepositoriesViewModel
import kotlinx.coroutines.launch
@ExperimentalPagingApi
class RepoListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RepoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // viewModel
        val viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        )[SearchRepositoriesViewModel::class.java]
        val adapter = ReposAdapter()

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(decoration)
        binding.list.adapter = adapter

        viewModel.getRepoList().observe(this,  Observer{
            lifecycleScope.launch {
                adapter.submitData(lifecycle, it)
                binding.retryButton.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }
}