package com.adsonsa.agendadortarefas.controller;

import com.adsonsa.agendadortarefas.business.TarefasService;
import com.adsonsa.agendadortarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
