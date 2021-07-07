package com.zsiegel.market.transactions

import com.zsiegel.market.graphql.schema.Account
import java.math.BigDecimal

data class Transaction(val id: String,
                       val title: String,
                       val description: String,
                       val value: BigDecimal,
                       val account: Account)