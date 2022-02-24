package com.root.millionPugsBank.service.crud

import com.root.millionPugsBank.model.Account
import com.root.millionPugsBank.service.crud.dto.AccountDTO

interface AccountService : CrudService<Account, Long> {

    fun saveByDto(accountDTO: AccountDTO): Account
}