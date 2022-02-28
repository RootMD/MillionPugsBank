package com.root.millionPugsBank.controller

import com.root.millionPugsBank.model.Account
import com.root.millionPugsBank.service.conversion.CurrencyConversionService
import com.root.millionPugsBank.service.crud.AccountService
import com.root.millionPugsBank.service.crud.dto.AccountDTO
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    val accountService: AccountService, val currencyConversionService: CurrencyConversionService
) {

    @PostMapping
    fun addAccount(@RequestBody accountDTO: AccountDTO): Account {
        return accountService.saveByDto(accountDTO)
    }

    @PatchMapping("/add")
    fun addBalance(@RequestBody @Validated accountDTO: AccountDTO): Account {
        return accountService.addToAccountBalance(accountDTO.id, accountDTO.balance)
    }

    @PatchMapping("/subtract")
    fun subtractBalance(@RequestBody accountDTO: AccountDTO): Account {
        return accountService.subtractFromAccountBalance(accountDTO.id, accountDTO.balance)
    }

    @DeleteMapping("/{id}")
    fun removeAccount(@PathVariable id: Long) {
        accountService.deleteByID(id)
    }

    @GetMapping("/list/{userId}")
    fun getAccountsByUserId(@PathVariable userId: Long): Collection<AccountDTO> {
        return accountService.getAccountsByUser(userId)
    }

    @GetMapping("/{id}")
    fun getAccountBalance(@PathVariable id: Long): Double {
        return accountService.findById(id).balance
    }

    @GetMapping("/{id}/{code}")
    fun getAccountBalanceAsCurrency(@PathVariable id: Long, @PathVariable code: String): Double {
        val balanceInPLN = accountService.findById(id).balance
        return currencyConversionService.convert(value = balanceInPLN, currencyTo = code)
    }
}