package com.root.millionPugsBank.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
    @Id
    @GeneratedValue
    val id: Long = 0,
    @Column(name = "balance")
    var balance: Double = 0.0,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("accountList")
    val user: User
)
