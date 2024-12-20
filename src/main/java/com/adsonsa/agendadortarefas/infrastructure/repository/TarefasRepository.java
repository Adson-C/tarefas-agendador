package com.adsonsa.agendadortarefas.infrastructure.repository;

import com.adsonsa.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.adsonsa.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String > {

    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacao(LocalDateTime dataInicial, LocalDateTime dataFinal,
                                                                          StatusNotificacaoEnum statusNotificacao);

    // metodo para deletar por email
    List<TarefasEntity> findByEmailUsuario(String email);

}
