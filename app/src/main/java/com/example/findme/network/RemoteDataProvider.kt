package com.example.findme.network

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

interface RemoteDataProvider {

    fun fetchUsersAndSave(
        success: Consumer<Boolean>,
        error: Consumer<Throwable>
    ): Disposable

}