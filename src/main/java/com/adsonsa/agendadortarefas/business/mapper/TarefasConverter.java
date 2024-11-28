package com.adsonsa.agendadortarefas.business.mapper;

import com.adsonsa.agendadortarefas.business.dto.TarefasDTO;
import com.adsonsa.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefaEntity(TarefasDTO dto);
    TarefasDTO paraTarefaDTO(TarefasEntity dto);

    List<TarefasEntity> paraListTarefaEntity(List<TarefasDTO> dto);

    List<TarefasDTO> paraListTarefaDTO(List<TarefasEntity> entities);

}
