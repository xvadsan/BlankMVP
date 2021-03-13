package com.xvadsan.blankmvp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xvadsan.blankmvp.data.database.model.Photo
import io.reactivex.Single


@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(photo: Photo)

    @Query("SELECT * FROM UsersPhoto")
    fun getItem(): Single<Photo>

}
