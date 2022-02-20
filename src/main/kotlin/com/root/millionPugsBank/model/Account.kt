package com.root.millionPugsBank.model

data class Account(
    val ID: Long,
    val balance: Double = 0.0,
    val User: User
)
