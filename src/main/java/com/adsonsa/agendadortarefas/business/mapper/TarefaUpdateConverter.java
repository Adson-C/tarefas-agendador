package com.adsonsa.agendadortarefas.business.mapper;

import com.adsonsa.agendadortarefas.business.dto.TarefasDTO;
import com.adsonsa.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    // se o dto stiver null usa o entity
    void updateTarefa(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
