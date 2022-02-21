package com.root.millionPugsBank.model

import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
    val balance: Double = 0.0,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) : BaseEntity() {
}
