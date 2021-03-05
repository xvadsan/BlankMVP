package com.xvadsan.blankmvp.base

interface BaseContract {

    interface View

    interface Presenter {
        fun start()
        fun stop()
        fun dispose()
    }

}
