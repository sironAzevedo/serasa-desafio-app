package com.serasa.desafio.model.mapper;

import com.serasa.desafio.model.dto.PessoaRequestDTO;
import com.serasa.desafio.model.entity.PessoaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mappings({
            //@Mapping(target="id", expression = "java(com.serasa.desafio.utils.GenerateUtil.code())"),
            @Mapping(target="dataInclusao", expression = "java(java.time.LocalDate.now())")
    })
    PessoaEntity from(PessoaRequestDTO dto);
}
