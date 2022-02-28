package com.root.millionPugsBank.service.crud.dto

data class AccountDTO(
    val id: Long,
    val balance: Double,
    val userID: Long
)