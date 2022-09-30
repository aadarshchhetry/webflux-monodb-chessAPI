package com.example.SpringWebfluxMongo.mapper.player;

import com.example.SpringWebfluxMongo.mapper.player.dto.Dto;
import com.example.SpringWebfluxMongo.mapper.player.entity.Entity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    Dto EntityToDto(Entity obj);

    Entity DtoToEntity(Dto obj);
}
