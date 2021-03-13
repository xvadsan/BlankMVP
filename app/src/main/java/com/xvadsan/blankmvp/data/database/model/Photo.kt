package com.xvadsan.blankmvp.data.database.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UsersPhoto")
class Photo(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "PhotoId")
    var photoId: Long,

    @NonNull
    @ColumnInfo(name = "Photo", typeAffinity = ColumnInfo.BLOB)
    var photo: ByteArray

)
