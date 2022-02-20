package com.root.millionPugsBank.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    val firstName: String,
    val lastName: String,
    val loginID: String,
    val password: String,
    @OneToMany(mappedBy = "user")
    val accountList: MutableList<Account> = ArrayList()
) {
    @Id
    @GeneratedValue
    val userId: Long = 0
    var email: String = ""
    var address: String = ""
}
