package com.example.pagingll2

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingll2.paging.api.GithubService
import com.example.pagingll2.paging.data.UserDataSource
import com.example.pagingll2.paging.data.UserDataSourceFactory
import com.example.pagingll2.paging.model.User
import io.reactivex.disposables.CompositeDisposable

class PagingViewModel : ViewModel() {

    lateinit var userList: LiveData<PagedList<User>>
    private val compositeDisposable = CompositeDisposable()

    private val pageSite = 10//

    private val sourceFactory: UserDataSourceFactory

    init {
        sourceFactory = UserDataSourceFactory(GithubService.getService(), compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSite)
            .setInitialLoadSizeHint(pageSite + 2)
            .setEnablePlaceholders(false)
            .build()
        userList = LivePagedListBuilder<Long, User>(sourceFactory, config).build()
    }

    fun getLoading() = Transformations.switchMap<UserDataSource, Boolean>(//switchMap lay ra ptu moi nhat
        sourceFactory.userDataSourceLiveData){it.isLoading}

    fun getRefreshState() = Transformations.switchMap<UserDataSource, Boolean>(
        sourceFactory.userDataSourceLiveData) {it.isRefresh}

    fun reset(){
        sourceFactory.userDataSourceLiveData.value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
//ViewModel sẽ chịu tạo PagedList và cung cấp cho hoạt động để nó
// có thể thay đổi dữ liệu mỗi khi request từ server