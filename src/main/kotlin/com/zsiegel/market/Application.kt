package com.zsiegel.market

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.zsiegel.market")
                .mainClass(Application.javaClass)
                .start()
    }
}
