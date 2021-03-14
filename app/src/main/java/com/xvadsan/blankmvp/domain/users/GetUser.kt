package com.xvadsan.blankmvp.domain.users

import com.xvadsan.blankmvp.data.database.model.EmbeddedUserPhoto
import com.xvadsan.blankmvp.domain.RoomStorageDataSource
import com.xvadsan.blankmvp.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetUser @Inject constructor(
    private val roomStorageDataSource: RoomStorageDataSource
) : SingleUseCase<EmbeddedUserPhoto, GetUser.Param>() {

    override fun execute(params: Param): Single<EmbeddedUserPhoto> = roomStorageDataSource.getUser(params.login, params.password)

    class Param(val login: String, val password: String)
}