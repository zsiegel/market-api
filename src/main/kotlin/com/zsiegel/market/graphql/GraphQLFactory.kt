package com.zsiegel.market.graphql

import com.zsiegel.market.database.AccountsTable
import com.zsiegel.market.graphql.schema.Account
import com.zsiegel.market.graphql.schema.Position
import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.toSchema
import graphql.GraphQL
import graphql.schema.DataFetchingEnvironment
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import javax.inject.Singleton

interface GraphQLQuery

@Factory
class GraphQLFactory {

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver,
                queryClasses: List<GraphQLQuery>): GraphQL {

        val config = SchemaGeneratorConfig(supportedPackages = listOf("com.zsiegel.market.graphql.schema"))
        val queries = queryClasses.map { TopLevelObject(it) }

        return GraphQL.newGraphQL(toSchema(config, queries, emptyList())).build()
    }

}

@Singleton
class PositionsFetcher : GraphQLQuery {

    private val positions: Map<String, List<Position>> = mapOf(
            "1" to listOf(Position(id = "1", ticker = "AAPL", shares = "100", price = "275.00", date = "2020-01-01")),
            "2" to listOf(Position(id = "1", ticker = "FORD", shares = "100", price = "3.00", date = "2020-01-01")))

    @GraphQLDescription("Gets a list of positions")
    fun positions(environment: DataFetchingEnvironment): List<Position> {
        val account: Account = environment.getSource()
        return positions[account.id] ?: emptyList()
    }
}

@Singleton
class AccountsFetcher(private val accountsTable: AccountsTable) : GraphQLQuery {

    @GraphQLDescription("Gets a list of accounts")
    fun accounts(environment: DataFetchingEnvironment): List<Account> {
        val accounts = accountsTable.accounts()
        return listOf(
                Account("1", "Savings", "This is my piggy bank"),
                Account("2", "Checking", "This is my disposable income")
        )
    }
}