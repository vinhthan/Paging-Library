package com.example.pagingll2.paging.data

import androidx.lifecycle.MutableLiveData
import com.example.pagingll2.paging.api.GithubService
import com.example.pagingll2.paging.model.User
import io.reactivex.disposables.CompositeDisposable

import androidx.paging.DataSource

class UserDataResourceFactory(private val githubService: GithubService,
                              private val compositeDisposable: CompositeDisposable): DataSource.Factory<Long, User>() {

    val userDataSourceLiveData = MutableLiveData<UserDataResource>()

    override fun create(): DataSource<Long, User> {
        val userDataSource = UserDataResource(githubService, compositeDisposable)
        userDataSourceLiveData.postValue(userDataSource)
        return userDataSource
    }


}