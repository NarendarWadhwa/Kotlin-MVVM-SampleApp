package com.example.findme.user_list

import com.example.findme.base.BaseNavigator
import com.example.findme.network.response.UserResponse


interface UserListNavigator : BaseNavigator {

    fun goToUserDetails(user: UserResponse)
}