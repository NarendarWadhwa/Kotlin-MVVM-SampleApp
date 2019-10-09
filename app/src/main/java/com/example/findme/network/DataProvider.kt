package com.example.findme.network

import com.example.findme.base.FindMeApp
import com.example.findme.db.UserDatabase
import com.example.findme.network.response.UserResponse
import com.example.findme.utils.isNetworkAvailable
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object DataProvider : RemoteDataProvider, LocalDataProvider {

    private val mServices by lazy { APIClient.getClient().create(APIInterface::class.java) }
    private val userDao by lazy { UserDatabase.getDB(FindMeApp.instance)!!.userDao() }

    private fun noInternetAvailable(error: Consumer<Throwable>) =
        error.accept(Throwable("No Internet Connection"))

    private fun getDefaultDisposable(): Disposable = object : Disposable {
        override fun isDisposed() = false
        override fun dispose() {}
    }

    override fun fetchUsersAndSave(success: Consumer<Boolean>, error: Consumer<Throwable>): Disposable {
        if (isNetworkAvailable()) {
            return mServices.fetchUsers()
                .flatMapCompletable {
                    if (it.isNotEmpty())
                        return@flatMapCompletable insertUsersInDb(it)
                    else
                        return@flatMapCompletable Completable.error(Throwable("No Data Available"))
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success.accept(true) }, { error.accept(it) })
        } else {
            noInternetAvailable(error)
            return getDefaultDisposable()
        }
    }

    override fun insertUsersInDb(usersList: List<UserResponse>) = userDao.insertUserList(usersList)

    override fun getUsersFromDb(
        success: Consumer<ArrayList<UserResponse>>,
        error: Consumer<Throwable>
    ): Disposable {
        return userDao.getUserList()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                success.accept(it as ArrayList<UserResponse>)
            }, error)
    }


    override fun updateUser(
        user: UserResponse, success: Consumer<Int>,
        error: Consumer<Throwable>
    ): Disposable {
        return userDao.updateUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { success.accept(it) }, error)
    }

    override fun getBookmarkedUsers(
        success: Consumer<ArrayList<UserResponse>>,
        error: Consumer<Throwable>
    ): Disposable {
        return userDao.getBookmarkedUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { success.accept(it as ArrayList<UserResponse>) }, error)
    }
}