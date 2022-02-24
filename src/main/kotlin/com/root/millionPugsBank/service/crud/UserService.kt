package com.root.millionPugsBank.service.crud

import com.root.millionPugsBank.model.User

interface UserService : CrudService<User, Long> {

    fun updateUser(entity: User): User
}