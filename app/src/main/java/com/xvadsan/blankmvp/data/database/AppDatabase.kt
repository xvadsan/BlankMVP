package com.xvadsan.blankmvp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xvadsan.blankmvp.data.database.dao.PhotoDao
import com.xvadsan.blankmvp.data.database.dao.UserDao
import com.xvadsan.blankmvp.data.database.model.Photo
import com.xvadsan.blankmvp.data.database.model.User

@Database(
    entities = [
        User::class,
        Photo::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao
}
