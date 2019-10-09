package com.example.findme.user_list

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findme.R
import com.example.findme.base.BaseFragment
import com.example.findme.databinding.FragmentUserFavsBinding


class UserFavsFragment : BaseFragment<FragmentUserFavsBinding, UserListViewModel>() {

    override fun getLayoutId() = R.layout.fragment_user_favs
    override fun getViewModel() = UserListViewModel::class.java

    override fun onBinding() {
        mBinding.rvFavUsers.layoutManager = LinearLayoutManager(activity)
        val userAdapter = UserListAdapter(activity as Context, mViewModel)
        mBinding.rvFavUsers.adapter = userAdapter

        mViewModel.bookmarkUserList.observe(viewLifecycleOwner, Observer {
            userAdapter.updateList(it)
            mBinding.tvNoFavs.visibility = if (it.size > 0) View.GONE else View.VISIBLE

        })
    }
}
