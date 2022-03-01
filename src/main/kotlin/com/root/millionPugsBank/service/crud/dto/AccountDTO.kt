package com.root.millionPugsBank.service.crud.dto

data class AccountDTO(
    val id: Long = 0,
    val balance: Double,
    val userID: Long
)