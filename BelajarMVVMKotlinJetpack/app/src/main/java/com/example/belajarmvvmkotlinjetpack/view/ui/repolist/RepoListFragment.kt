package com.example.belajarmvvmkotlinjetpack.view.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belajarmvvmkotlinjetpack.databinding.RepoListFragmentBinding
import com.example.belajarmvvmkotlinjetpack.view.adapter.RepoListAdapter
import kotlinx.android.synthetic.main.repo_list_fragment.*

class RepoListFragment : Fragment() {

    private lateinit var viewDataBinding: RepoListFragmentBinding
    private lateinit var adapterRepoList: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = RepoListFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@RepoListFragment).get(RepoListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchRepoList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.repoListLive?.observe(viewLifecycleOwner, Observer {
            adapterRepoList.updateRepoList(it)
        })
        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapterRepoList = RepoListAdapter(viewModel)
            val linearLayoutMManager = LinearLayoutManager(context)
            repo_list_rv.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutMManager
                adapter = adapterRepoList
                addItemDecoration(DividerItemDecoration(activity, linearLayoutMManager.orientation))
            }
        }
    }
}
