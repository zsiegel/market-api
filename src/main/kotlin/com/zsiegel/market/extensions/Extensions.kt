package com.zsiegel.market.extensions

import io.micronaut.core.io.ResourceResolver
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

fun ResourceResolver.getResourceAsReader(path: String): Reader {
    return BufferedReader(InputStreamReader(this.getResourceAsStream(path).get()))
}
