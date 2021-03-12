package com.xvadsan.blankmvp.data.database.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UsersDB")
class User(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    var id: Long,

    @NonNull
    @ColumnInfo(name = "USER")
    var user: String,

    @ColumnInfo(name = "LOGIN")
    var login: String,

    @ColumnInfo(name = "PASSWORD")
    var password: String

)
