package com.xvadsan.blankmvp.domain

import io.reactivex.Completable


interface RoomStorageDataSource {
    fun addUser(login: String, password: String): Completable
    fun addPhoto(photo: ByteArray): Completable
}