package com.example.findme.user_list

import androidx.lifecycle.MutableLiveData
import com.example.findme.base.BaseViewModel
import com.example.findme.network.DataProvider
import com.example.findme.network.response.UserResponse
import io.reactivex.functions.Consumer


class UserListViewModel : BaseViewModel<UserListNavigator>() {

    val userList = MutableLiveData<ArrayList<UserResponse>>()
    val bookmarkUserList = MutableLiveData<ArrayList<UserResponse>>()

    fun fetchUsersAndSaveInDB() {
        dialogMessage.value = "Fetching Data..."
        dialogVisibility.value = true
        mDisposable.add(DataProvider.fetchUsersAndSave(Consumer {
            getUsersFromDB()
        }, Consumer { checkError(it) }))
    }

    private fun getUsersFromDB() {
        mDisposable.add(DataProvider.getUsersFromDb(Consumer {
            dialogVisibility.value = false
            userList.value = it
        }, Consumer { checkError(it) }))
    }

    fun bookmarkUser(userItem: UserResponse) {
        dialogMessage.value = "Updating..."
        dialogVisibility.value = true
        mDisposable.add(DataProvider.updateUser(userItem, Consumer {
            dialogVisibility.value = false
            if (it > -1) {
                getBookmarkedUsers()
            }
        }, Consumer { checkError(it) }))
    }

    fun getBookmarkedUsers() {
        dialogMessage.value = "Fetching..."
        dialogVisibility.value = true
        mDisposable.add(DataProvider.getBookmarkedUsers(Consumer {
            dialogVisibility.value = false
            bookmarkUserList.value = it
        }, Consumer { checkError(it) }))
    }

    fun goToUserDetails(user: UserResponse) {
        mNavigator!!.goToUserDetails(user)
    }

}