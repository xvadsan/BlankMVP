package com.xvadsan.blankmvp.domain.users

import com.xvadsan.blankmvp.domain.CompletableUseCase
import com.xvadsan.blankmvp.domain.RoomStorageDataSource
import io.reactivex.Completable
import javax.inject.Inject

class AddPhoto @Inject constructor(
    private val roomStorageDataSource: RoomStorageDataSource
) : CompletableUseCase<AddPhoto.Param>() {

    override fun execute(params: Param): Completable = roomStorageDataSource.addPhoto(params.image)

    class Param(val image: ByteArray)
}