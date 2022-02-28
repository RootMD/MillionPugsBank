package com.root.millionPugsBank.repository

import com.root.millionPugsBank.model.Account
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Long> {

    @Query(value = "select * from account where user_id = ?1", nativeQuery = true)
    fun findByUserId(userID: Long): Collection<Account>
}