package com.zsiegel.market.endpoints

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/")
class HelloController {

    @Get("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun index(): String {
        return "Hello from market-api"
    }

}