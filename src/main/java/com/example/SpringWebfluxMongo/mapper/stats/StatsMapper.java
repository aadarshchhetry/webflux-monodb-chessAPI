package com.example.SpringWebfluxMongo.mapper.stats;

import com.example.SpringWebfluxMongo.mapper.stats.dto.StatsDto;
import com.example.SpringWebfluxMongo.mapper.stats.entity.StatsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatsMapper {
    StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);

    StatsDto EntityToDto(StatsEntity obj);

    StatsEntity DtoToEntity(StatsDto obj);

}
