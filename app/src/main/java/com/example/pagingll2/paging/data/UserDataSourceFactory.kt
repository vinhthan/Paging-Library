package com.example.pagingll2.paging.data

import androidx.lifecycle.MutableLiveData
import com.example.pagingll2.paging.api.GithubService
import com.example.pagingll2.paging.model.User
import io.reactivex.disposables.CompositeDisposable

import androidx.paging.DataSource

class UserDataSourceFactory(
    private val githubService: GithubService,
    private val compositeDisposable: CompositeDisposable): DataSource.Factory<Long, User>() {

    val userDataSourceLiveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Long, User> {
        val userDataSource = UserDataSource(githubService, compositeDisposable)
        userDataSourceLiveData.postValue(userDataSource)
        return userDataSource
    }


}

//Dùng để khởi tạo DataSource, cung cấp dữ liệu cho PagedList