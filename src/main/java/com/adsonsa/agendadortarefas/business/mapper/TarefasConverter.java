package com.adsonsa.agendadortarefas.business.mapper;

import com.adsonsa.agendadortarefas.business.dto.TarefasDTO;
import com.adsonsa.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);
    TarefasDTO paraTarefaDTO(TarefasEntity dto);

}
