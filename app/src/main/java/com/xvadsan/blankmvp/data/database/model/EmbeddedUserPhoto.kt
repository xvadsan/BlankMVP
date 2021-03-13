package com.xvadsan.blankmvp.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

class EmbeddedUserPhoto {

    @Embedded
    lateinit var user: User

    @Relation(parentColumn = "Id", entity = Photo::class, entityColumn = "PhotoId")
    var photo: Photo? = null
}
