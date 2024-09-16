package com.carometro.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.carometro.dto.AlunoDto;
import com.carometro.model.Aluno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlunoMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "comentario", ignore = true)
  @Mapping(target = "experiencias", ignore = true)
  @Mapping(target = "turma", ignore = true)
  void updateAlunoFromDto(AlunoDto dto, @MappingTarget Aluno aluno);
}
