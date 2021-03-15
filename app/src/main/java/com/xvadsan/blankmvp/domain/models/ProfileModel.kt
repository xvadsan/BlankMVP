package com.xvadsan.blankmvp.domain.models

class ProfileModel(
    var id: Long,
    var login: String,
    var password: String,
    var photo: ByteArray?
)