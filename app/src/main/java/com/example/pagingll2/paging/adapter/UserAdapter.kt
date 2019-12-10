package com.example.pagingll2.paging.adapter


import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingll2.R
import com.example.pagingll2.paging.model.User

class UserAdapter(
    private val retryCallback: () -> Unit) : PagedListAdapter<User, RecyclerView.ViewHolder>(UserDiffCallback) {

    companion object{
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }

    }

    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            R.layout.item_paging_user -> UserItemViewHolder.create(parent)
            R.layout.item_paging_loading -> NetworkStateItemViewHolder.create(parent)
            else -> throw IllegalAccessException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         when (getItemViewType(position)){
             R.layout.item_paging_user -> (holder as? UserItemViewHolder)?.bindTo(getItem(position))
             R.layout.item_paging_loading -> (holder as? NetworkStateItemViewHolder)?.binTo(isLoading)
         }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == itemCount -1){
            R.layout.item_paging_loading
        }else{
            R.layout.item_paging_user
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isLoading) 1 else 0
    }

    fun setLoading(isLoading: Boolean?){
        isLoading?.let {
            currentList?.isNotEmpty()?.let {
                val previousState = this.isLoading
                this.isLoading = isLoading
                if (previousState != isLoading){
                    if (previousState != isLoading){
                        notifyItemRemoved(super.getItemCount())
                    }else{
                        notifyItemRemoved(super.getItemCount())
                    }
                }
            }
            notifyItemRemoved(super.getItemCount())
        }
    }




}