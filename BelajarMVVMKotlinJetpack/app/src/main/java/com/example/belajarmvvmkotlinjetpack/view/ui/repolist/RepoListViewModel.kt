package com.example.belajarmvvmkotlinjetpack.view.ui.repolist

import androidx.lifecycle.MutableLiveData
import com.example.belajarmvvmkotlinjetpack.model.Item
import com.example.belajarmvvmkotlinjetpack.model.api.RepoRepository
import com.example.belajarmvvmkotlinjetpack.view.base.BaseViewModel

class RepoListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Item>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepoRepository.getInstance().getRepoList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                repoListLive.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}
