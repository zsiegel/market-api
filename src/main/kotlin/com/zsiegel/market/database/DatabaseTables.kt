package com.zsiegel.market.database

import com.zsiegel.market.graphql.schema.Account
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Prototype
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.jdbi.v3.sqlobject.statement.SqlQuery

@Factory
class DatabaseTablesFactory(private val jdbi: Jdbi) {

    @Prototype
    fun accounts(): AccountsTable {
        return jdbi.onDemand(AccountsTable::class)
    }
}

interface AccountsTable {

    @SqlQuery("select * from accounts")
    fun accounts(): List<Account>

}