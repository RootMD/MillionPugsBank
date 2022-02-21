package com.root.millionPugsBank.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    val firstName: String,
    val lastName: String,
    val loginID: String,
    val password: String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    val accountList: MutableSet<Account> = HashSet()
) : BaseEntity() {
    var email: String = ""
    var address: String = ""
}
