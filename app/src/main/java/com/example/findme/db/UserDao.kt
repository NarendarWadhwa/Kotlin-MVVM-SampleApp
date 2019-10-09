package com.example.findme.db

import androidx.room.*
import com.example.findme.network.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(userList: List<UserResponse>): Completable

    @Query("SELECT * FROM user")
    fun getUserList(): Single<List<UserResponse>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: UserResponse): Single<Int>

    @Query("SELECT * FROM user WHERE is_fav = 1")
    fun getBookmarkedUsers(): Single<List<UserResponse>>

    @Query("DELETE from user")
    fun deleteAll(): Completable
}