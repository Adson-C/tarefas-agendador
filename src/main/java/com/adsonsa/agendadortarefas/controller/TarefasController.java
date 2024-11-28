package com.adsonsa.agendadortarefas.controller;

import com.adsonsa.agendadortarefas.business.TarefasService;
import com.adsonsa.agendadortarefas.business.dto.TarefasDTO;
import com.adsonsa.agendadortarefas.infrastructure.enums.StatusNotificacaoEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefaService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravraTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token,dto));
    }
    // metodo de para pegar eventos
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefaAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefaService.buscarTarefaAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    // buscar Tarefas por email
    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }
    // Deletar por id
    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id) {
        tarefaService.deletarTarefaPorId(id);
        return ResponseEntity.ok().build();
    }
    // medotodo de alterar  tarefas
    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnums Status,
                                                               @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatus(id,Status));
    }
    // metodo de alterar tarefas Put
    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto, @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefas(id,dto));
    }
}

