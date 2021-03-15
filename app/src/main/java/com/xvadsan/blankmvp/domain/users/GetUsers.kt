package com.xvadsan.blankmvp.domain.users

import com.xvadsan.blankmvp.data.database.model.EmbeddedUserPhoto
import com.xvadsan.blankmvp.domain.RoomStorageDataSource
import com.xvadsan.blankmvp.domain.SingleUseCase
import com.xvadsan.blankmvp.domain.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val roomStorageDataSource: RoomStorageDataSource
) : SingleUseCase<List<EmbeddedUserPhoto>, UseCase.None>() {

    override fun execute(params: UseCase.None): Single<List<EmbeddedUserPhoto>> = roomStorageDataSource.getUsers()

}