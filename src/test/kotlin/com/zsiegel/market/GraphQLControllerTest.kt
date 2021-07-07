package com.zsiegel.market

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

//TODO Switch to use TestContainer rather than manual docker-compose setup

@MicronautTest
class GraphQLControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    val query = """
    {
    accounts {
        id
        name
    }
    }    
    """.trimIndent()

    data class QueryRequest(val query: String)
    data class Response(val data: Accounts)
    data class Accounts(val accounts: List<AccountResponse>)
    data class AccountResponse(val id: String, val name: String)

    @Test
    fun queryForAccountsAndPositions() {
        val response = client.toBlocking()
            .exchange(
                HttpRequest.POST("/graphql", QueryRequest(query)), Response::class.java
                                             )

        Assertions.assertEquals(2, response.body.get().data.accounts.size)
        Assertions.assertEquals("1", response.body.get().data.accounts[0].id)
    }
}