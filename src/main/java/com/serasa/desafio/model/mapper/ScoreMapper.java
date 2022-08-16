package com.serasa.desafio.model.mapper;

import com.serasa.desafio.model.dto.ScoreRequestDTO;
import com.serasa.desafio.model.entity.ScoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScoreMapper {

    ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);

    @Mappings({
            //@Mapping(target="id", expression = "java(com.serasa.desafio.utils.GenerateUtil.code())"),
            @Mapping(target="descricao", expression = "java(dto.getScoreDescricao())")
    })
    ScoreEntity from(ScoreRequestDTO dto);
}
