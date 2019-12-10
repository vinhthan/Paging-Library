package com.example.pagingll2.paging.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.example.pagingll2.paging.api.GithubService
import com.example.pagingll2.paging.model.User
import io.reactivex.disposables.CompositeDisposable

class UserDataSource(
    private val githubService: GithubService,
    private val compositeDisposable: CompositeDisposable) : ItemKeyedDataSource<Long, User>() {

    val isLoading = MutableLiveData<Boolean>()
    val isRefresh = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        isLoading.postValue(true)
        isRefresh.postValue(true)
        compositeDisposable.add(githubService.getUsers(1, params.requestedLoadSize)
            .subscribe({
                isLoading.postValue(false)
                isRefresh.postValue(false)
                callback.onResult(it)
            }, {
                isLoading.postValue(false)
                isRefresh.postValue(false)
            }))
    }

    //dc goi khi list dat gioi han
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        isLoading.postValue(true)
        compositeDisposable.add(githubService.getUsers(params.key, params.requestedLoadSize)
            .subscribe({users ->
                isLoading.postValue(false)
                callback.onResult(users)
            }, {
                isLoading.postValue(false)
            }))
    }

    // dc goi sau loadInitial
    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {

    }

    override fun getKey(item: User): Long {
        return item.id.toLong()
    }


}