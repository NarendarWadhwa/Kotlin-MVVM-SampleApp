package com.example.findme.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.findme.network.response.UserResponse

@Database(entities = [UserResponse::class], version = 1,exportSchema  = false)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: UserDatabase? = null
        fun getDB(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                synchronized(UserDatabase) {
                    INSTANCE = Room.databaseBuilder(
                        context, UserDatabase::class.java, "find_me.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

    }

    abstract fun userDao(): UserDao
}