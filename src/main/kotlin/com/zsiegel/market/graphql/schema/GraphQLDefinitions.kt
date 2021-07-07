package com.zsiegel.market.graphql.schema

data class Account(val id: String,
                   val name: String,
                   val description: String = "",
                   val taxable: Boolean = true)

data class Position(val id: String,
                    val ticker: String,
                    val shares: String,
                    val price: String,
                    val date: String)