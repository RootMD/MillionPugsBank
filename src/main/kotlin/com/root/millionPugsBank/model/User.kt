package com.root.millionPugsBank.model

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val loginID: String,
    val password: String,
    val accountList: MutableList<Account> = ArrayList()
) {
    var email: String = ""
    var address: String = ""
}
