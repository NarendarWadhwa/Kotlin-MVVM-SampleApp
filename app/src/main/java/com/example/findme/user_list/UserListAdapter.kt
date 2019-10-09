package com.example.findme.user_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findme.R
import com.example.findme.databinding.ItemUserBinding
import com.example.findme.network.response.UserResponse

class UserListAdapter(
    val context: Context,
    private val viewModel: UserListViewModel,
    private val showBookmark: Boolean = false
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val userList = ArrayList<UserResponse>()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemUserBinding = ItemUserBinding.inflate(mInflater, parent, false)
        return UserViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]
        holder.itemBinding.user = item
        holder.itemBinding.showBookmark = showBookmark
        holder.itemBinding.ivBookmark.setOnClickListener {
            if (item.isFav == 0) {
                holder.itemBinding.ivBookmark.setBackgroundResource(R.drawable.ic_bookmark)
                item.isFav = 1
            } else {
                holder.itemBinding.ivBookmark.setBackgroundResource(R.drawable.ic_bookmark_border)
                item.isFav = 0
            }
            viewModel.bookmarkUser(item)
        }
        holder.itemBinding.itemLayout.setOnClickListener {
            viewModel.goToUserDetails(item)
        }
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    fun updateList(userList: ArrayList<UserResponse>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = userList.size


    class UserViewHolder(val itemBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemBinding.root)
}