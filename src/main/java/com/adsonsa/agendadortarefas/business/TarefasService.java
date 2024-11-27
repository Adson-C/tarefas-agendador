package com.adsonsa.agendadortarefas.business;

import com.adsonsa.agendadortarefas.business.dto.TarefasDTO;
import com.adsonsa.agendadortarefas.business.mapper.TarefasConverter;
import com.adsonsa.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.adsonsa.agendadortarefas.infrastructure.enums.StatusNotificacaoEnums;
import com.adsonsa.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.adsonsa.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;

    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token , TarefasDTO dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        // usuario setando a dataCriacao
        dto.setDataCriacao(LocalDateTime.now());
        // usuario setando o status
        dto.setStatusNotificacao(StatusNotificacaoEnums.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity)
        );
    }

    // metodo para buscar por tarefas por dataCriacao
    public List<TarefasDTO> buscarTarefaAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaConverter.paraListTarefaDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }
    // metodo para deletar por email
    public List<TarefasDTO> buscaTarefaPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        return tarefaConverter.paraListTarefaDTO(
                tarefasRepository.findByEmailUsuario(email));
//        List<TarefasEntity> listatarefa = tarefasRepository.findByEmailUsuario(email);
//
//        return tarefaConverter.paraListTarefaDTO(listatarefa);
    }

}
