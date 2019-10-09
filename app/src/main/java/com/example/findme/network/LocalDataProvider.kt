package com.example.findme.network

import com.example.findme.network.response.UserResponse
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

interface LocalDataProvider {

    fun insertUsersInDb(usersList: List<UserResponse>): Completable

    fun getUsersFromDb(
        success: Consumer<ArrayList<UserResponse>>,
        error: Consumer<Throwable>
    ): Disposable

    fun updateUser(
        user: UserResponse,
        success: Consumer<Int>,
        error: Consumer<Throwable>
    ): Disposable

    fun getBookmarkedUsers(
        success: Consumer<ArrayList<UserResponse>>,
        error: Consumer<Throwable>
    ): Disposable

}