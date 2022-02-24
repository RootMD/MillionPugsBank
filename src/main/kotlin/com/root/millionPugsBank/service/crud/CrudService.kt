package com.root.millionPugsBank.service.crud

interface CrudService<T, ID> {

    fun findAll(): List<T>

    fun findById(id: ID): T

    fun save(entity: T): T

    fun delete(entity: T)

    fun deleteByID(id: ID)
}