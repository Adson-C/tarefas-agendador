package com.adsonsa.agendadortarefas.business;

import com.adsonsa.agendadortarefas.business.dto.TarefasDTO;
import com.adsonsa.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.adsonsa.agendadortarefas.business.mapper.TarefasConverter;
import com.adsonsa.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.adsonsa.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.adsonsa.agendadortarefas.infrastructure.exceptions.ResoucerNotFoundException;
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
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token , TarefasDTO dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        // usuario setando a dataCriacao
        dto.setDataCriacao(LocalDateTime.now());
        // usuario setando o status
        dto.setStatusNotificacao(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity)
        );
    }

    // metodo para buscar por tarefas por dataCriacao
    public List<TarefasDTO> buscarTarefaAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaConverter.paraListTarefaDTO(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacao(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
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

    //  deleta tarefa por Id
    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResoucerNotFoundException e) {
            throw new ResoucerNotFoundException("Erro ao deletar tarefa por id, id inexistente" + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(String id, StatusNotificacaoEnum status) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResoucerNotFoundException("Tarefa nao encontrada" + id));
            entity.setStatusNotificacao(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }catch (ResoucerNotFoundException e) {
            throw new ResoucerNotFoundException("Erro ao alterar status, id inexistente" + id, e.getCause());
        }
    }
    // upadate de tarefas **
    public TarefasDTO updateTarefas(String id, TarefasDTO dto) {
        try {

            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResoucerNotFoundException("Tarefa nao encontrada" + id));
            tarefaUpdateConverter.updateTarefa(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResoucerNotFoundException e) {
            throw new ResoucerNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }
    }

}
