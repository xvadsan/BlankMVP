package com.xvadsan.blankmvp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xvadsan.blankmvp.data.database.model.EmbeddedUserPhoto
import com.xvadsan.blankmvp.data.database.model.User
import io.reactivex.Single


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("SELECT * FROM UsersDB WHERE Login = :login AND Password = :password")
    fun getUser(login: String, password: String): User

    @Query("SELECT * FROM UsersDB WHERE Login = :login AND Password = :password ")
    fun getUserWithPhoto(login: String, password: String): EmbeddedUserPhoto
}
