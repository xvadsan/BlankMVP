package com.xvadsan.blankmvp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xvadsan.blankmvp.data.database.model.User
import io.reactivex.Single


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("SELECT * FROM UsersDB WHERE LOGIN = :login AND PASSWORD = :password ")
    fun getUser(login: String, password: String): Single<User>

}
