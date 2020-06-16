package com.example.belajarmvvmkotlinjetpack.model.api

import com.example.belajarmvvmkotlinjetpack.model.GitResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {
    private val service: ApiService by lazy {
        ApiClient.get().create(ApiService::class.java)
    }
    fun getRepoList(onResult: (isSuccess: Boolean, response: GitResponse?) -> Unit) {
        service.getRepo().enqueue(
            object : Callback<GitResponse> {
                override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                    onResult(false, null)
                }

                override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                    if(response.isSuccessful) {
                        onResult(true, response.body())
                    } else {
                        onResult(false, null)
                    }
                }
            }
        )
    }

    companion object {
        private var INSTANCE: RepoRepository? = null
        fun getInstance() = INSTANCE ?: RepoRepository().also {
            INSTANCE = it
        }
    }
}