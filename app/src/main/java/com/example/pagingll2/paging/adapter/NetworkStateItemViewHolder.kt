package com.example.pagingll2.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingll2.R
import kotlinx.android.synthetic.main.item_paging_loading.view.*

class NetworkStateItemViewHolder (view: View) : RecyclerView.ViewHolder(view){

    companion object{
        fun create(parent: ViewGroup): NetworkStateItemViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paging_loading,
                parent, false)
            return NetworkStateItemViewHolder(view)
        }
    }

    fun binTo(isLoading: Boolean){
        itemView.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }



}