package com.example.pagingll2.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pagingll2.R
import com.example.pagingll2.paging.model.User
import kotlinx.android.synthetic.main.item_paging_user.view.*

class UserItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object{
        fun create(parent: ViewGroup): UserItemViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_paging_user, parent, false)
            return UserItemViewHolder(view)
        }
    }

    fun bindTo(user: User?){
        user?.let {
            itemView.UserName.text = user.login
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(itemView.UserAvatar)
            itemView.siteAdminIcon.visibility = if (user.siteAdmin) View.VISIBLE else View.GONE
        }
    }

}