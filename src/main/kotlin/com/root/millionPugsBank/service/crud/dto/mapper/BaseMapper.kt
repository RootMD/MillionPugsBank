package com.root.millionPugsBank.service.crud.dto.mapper

interface BaseMapper<DTO, DOMAIN> {

    fun entityDtoToEntity(entityDto: DTO): DOMAIN

    fun entityToEntityDto(entity: DOMAIN): DTO
}