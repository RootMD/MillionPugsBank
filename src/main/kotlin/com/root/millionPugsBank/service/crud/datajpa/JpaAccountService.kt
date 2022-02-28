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
            ?: throw NoSuchElementException("Could not find a account with id $id")
    }

    override fun saveByDto(accountDTO: AccountDTO): Account {
        val account = accountMapper.entityDtoToEntity(accountDTO)
        return accountRepository.save(account)
    }

    override fun subtractFromAccountBalance(id: Long, value: Double): Account {
        val foundAccount = findById(id)
        if (foundAccount.balance < value) throw IllegalStateException("Non-sufficient Funds, not have enough money($foundAccount.balance) to cover transaction($value).")
        foundAccount.balance -= value
        return accountRepository.save(foundAccount)
    }

    override fun addToAccountBalance(id: Long, value: Double): Account {
        val foundAccount = findById(id)
        foundAccount.balance += value
        return accountRepository.save(foundAccount)
    }

    override fun getAccountsByUser(userID: Long): Collection<AccountDTO> {
        val foundAccounts = accountRepository.findByUserId(userID)
        return foundAccounts.map { account -> accountMapper.entityToEntityDto(account) }
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