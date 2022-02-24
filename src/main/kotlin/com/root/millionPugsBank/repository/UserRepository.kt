package com.root.millionPugsBank.repository

import com.root.millionPugsBank.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>