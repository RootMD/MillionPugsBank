package com.root.millionPugsBank.service.crud.datajpa

import com.root.millionPugsBank.model.User
import com.root.millionPugsBank.repository.UserRepository
import com.root.millionPugsBank.service.crud.UserService
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class JpaUserService(
    val userRepository: UserRepository
) : UserService {

    override fun findAll(): List<User> {
        val users = ArrayList<User>()
        userRepository.findAll().forEach(users::add)
        return users
    }

    override fun findById(id: Long): User {
        return userRepository.findById(id).orElse(null)
            ?: throw NoSuchElementException("Could not find a user with id $id")
    }

    override fun save(entity: User): User {
        if(findAll().any { it.loginID == entity.loginID })
            throw IllegalArgumentException("User with login number ${entity.loginID} already exists")
        val hashedUser = when (entity.accountList) {
            null -> entity.copy(
                password = BCrypt.hashpw(entity.password, BCrypt.gensalt()),
                accountList = mutableListOf()
            )
            else -> entity.copy(password = BCrypt.hashpw(entity.password, BCrypt.gensalt()))
        }
        return userRepository.save(hashedUser)
    }

    override fun delete(entity: User) {
        userRepository.delete(entity)
    }

    override fun deleteByID(id: Long) {
        userRepository.deleteById(id)
    }

    override fun updateUser(entity: User): User {
        val userFound = findById(entity.id)
        val newUser = userFound.copy(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            loginID = entity.loginID,
            password = entity.password,
            email = entity.email,
            address = entity.address
        )
        return userRepository.save(newUser)
    }
}