package com.xvadsan.blankmvp.domain

import com.xvadsan.blankmvp.data.database.model.EmbeddedUserPhoto
import io.reactivex.Completable
import io.reactivex.Single


interface RoomStorageDataSource {
    fun addUser(login: String, password: String): Completable
    fun addPhoto(photo: ByteArray): Completable
    fun getUser(login: String, password: String): Single<EmbeddedUserPhoto>
    fun getUsers(): Single<List<EmbeddedUserPhoto>>
}