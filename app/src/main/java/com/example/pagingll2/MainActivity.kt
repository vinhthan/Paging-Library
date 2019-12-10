package com.example.pagingll2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingll2.paging.adapter.UserAdapter
import com.example.pagingll2.paging.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(PagingViewModel::class.java) }
    private val userAdapter by lazy {
        UserAdapter{

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        srPaging.setOnRefreshListener { viewModel.reset() }

        initAdapter()
    }

    private fun initAdapter() {
        usersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        usersRecyclerView.adapter = userAdapter

        viewModel.userList.observe(this, Observer<PagedList<User>>{userAdapter.submitList(it)})
        viewModel.getLoading().observe(this,
            Observer<Boolean>{userAdapter.setLoading(it)})
        viewModel.getRefreshState().observe(this, Observer<Boolean>{
            srPaging.isRefreshing = it?:false
        })
    }
}
