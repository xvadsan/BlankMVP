package com.xvadsan.blankmvp.domain.users

import com.xvadsan.blankmvp.domain.CompletableUseCase
import com.xvadsan.blankmvp.domain.RoomStorageDataSource
import io.reactivex.Completable
import javax.inject.Inject

class AddUser @Inject constructor(
    private val roomStorageDataSource: RoomStorageDataSource
) : CompletableUseCase<AddUser.Param>() {

    override fun execute(params: Param): Completable = roomStorageDataSource.addUser(params.login, params.password)

    class Param(val login: String, val password: String)
}