package com.xvadsan.blankmvp.data.database.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UsersDB")
class User(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    var id: Long = 0,

    @ColumnInfo(name = "Login")
    var login: String,

    @ColumnInfo(name = "Password")
    var password: String
)
