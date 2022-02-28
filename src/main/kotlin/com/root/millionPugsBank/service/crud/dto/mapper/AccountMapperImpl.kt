package com.root.millionPugsBank.service.crud.dto.mapper

import com.root.millionPugsBank.model.Account
import com.root.millionPugsBank.service.crud.UserService
import com.root.millionPugsBank.service.crud.dto.AccountDTO
import org.springframework.stereotype.Service

@Service
class AccountMapperImpl(
    val userService: UserService
) : AccountMapper {
    override fun entityDtoToEntity(entityDto: AccountDTO): Account {
        val foundUser = userService.findById(entityDto.userID)
        return Account(id = entityDto.id, balance = entityDto.balance, user = foundUser)
    }

    override fun entityToEntityDto(entity: Account): AccountDTO {
        return AccountDTO(entity.id, entity.balance, entity.user.id)
    }
}