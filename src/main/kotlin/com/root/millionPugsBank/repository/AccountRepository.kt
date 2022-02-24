package com.root.millionPugsBank.repository

import com.root.millionPugsBank.model.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account,Long>