package com.xvadsan.blankmvp.data

import com.xvadsan.blankmvp.data.database.AppDatabase
import com.xvadsan.blankmvp.data.database.model.EmbeddedUserPhoto
import com.xvadsan.blankmvp.data.database.model.Photo
import com.xvadsan.blankmvp.data.database.model.User
import com.xvadsan.blankmvp.domain.RoomStorageDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RoomStorageRepository @Inject constructor(
    private val database: AppDatabase
) : RoomStorageDataSource {

    override fun addUser(login: String, password: String): Completable {
        return Completable.fromCallable {
            database.userDao().addUser(User(login = login, password = password))
        }
    }

    override fun addPhoto(photo: ByteArray): Completable {
        return Completable.fromCallable {
            database.photoDao().addItem(photo = Photo(photoId = 0, photo = photo))
        }
    }

    override fun getUser(login: String, password: String): Single<EmbeddedUserPhoto> = Single.fromCallable {
        database.userDao().getUserWithPhoto(login = login, password = password)
    }

    override fun getUsers(): Single<List<EmbeddedUserPhoto>> = Single.fromCallable {
        database.userDao().getUsersWithPhoto()
    }
}