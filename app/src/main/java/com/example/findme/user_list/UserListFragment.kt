package com.example.findme.user_list


import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findme.R
import com.example.findme.base.BaseFragment
import com.example.findme.databinding.FragmentUserListBinding

class UserListFragment : BaseFragment<FragmentUserListBinding, UserListViewModel>() {

    override fun getLayoutId() = R.layout.fragment_user_list
    override fun getViewModel() = UserListViewModel::class.java

    override fun onBinding() {
        mBinding.rvUsers.layoutManager = LinearLayoutManager(activity)
        val userAdapter = UserListAdapter(activity as Context, mViewModel, true)
        mBinding.rvUsers.adapter = userAdapter

        mViewModel.userList.observe(viewLifecycleOwner, Observer {
            userAdapter.updateList(it)
        })

        mViewModel.fetchUsersAndSaveInDB()
    }

}
