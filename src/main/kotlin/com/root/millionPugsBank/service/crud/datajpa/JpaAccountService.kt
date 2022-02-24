package com.root.millionPugsBank.service.crud.datajpa

import com.root.millionPugsBank.model.Account
import com.root.millionPugsBank.repository.AccountRepository
import com.root.millionPugsBank.service.crud.AccountService
import com.root.millionPugsBank.service.crud.dto.AccountDTO
import com.root.millionPugsBank.service.crud.dto.mapper.AccountMapper
import org.springframework.stereotype.Service

@Service
class JpaAccountService(
    val accountRepository: AccountRepository,
    val accountMapper: AccountMapper
) : AccountService {

    override fun findAll(): List<Account> {
        val accounts = ArrayList<Account>()
        accountRepository.findAll().forEach(accounts::add)
        return accounts
    }

    override fun findById(id: Long): Account {
        return accountRepository.findById(id).orElse(null)
    }

    override fun saveByDto(accountDTO: AccountDTO): Account {
        val account = accountMapper.entityDtoToEntity(accountDTO)
        return accountRepository.save(account)
    }

    override fun save(entity: Account): Account {
        return accountRepository.save(entity)
    }

    override fun delete(entity: Account) {
        accountRepository.delete(entity)
    }

    override fun deleteByID(id: Long) {
        accountRepository.deleteById(id)
    }
}