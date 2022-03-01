package com.root.millionPugsBank.controller

import com.root.millionPugsBank.model.Account
import com.root.millionPugsBank.model.User
import com.root.millionPugsBank.service.crud.AccountService
import com.root.millionPugsBank.service.crud.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    val userService: UserService,
    val accountService: AccountService
) {
    @GetMapping
    fun getUsers(): Collection<User> = userService.findAll()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User {
        return userService.findById(id)
    }

    @PostMapping
    fun addUser(@RequestBody user: User): User {
        val createdUser = userService.save(user)
        accountService.save(Account(user = createdUser))
        return createdUser
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteByID(id)
    }

    @PatchMapping
    fun updateUser(@RequestBody user: User): User {
        return userService.updateUser(user)
    }

}