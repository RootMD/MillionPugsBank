package com.root.millionPugsBank.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue
    val id: Long = 0,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "login_id")
    val loginID: String,
    @Column(name = "password")
    val password: String,
    @Column(name = "account_list")
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    @JsonIgnoreProperties("user")
    val accountList: MutableList<Account> = mutableListOf(),
    @Column(name = "email")
    val email: String = "",
    @Column(name = "address")
    val address: String = "",

)
