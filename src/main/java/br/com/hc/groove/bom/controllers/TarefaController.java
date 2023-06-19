package br.com.hc.groove.bom.controllers;

import br.com.hc.groove.bom.domain.models.dtos.TarefaDTO;
import br.com.hc.groove.bom.domain.models.forms.TarefaForm;
import br.com.hc.groove.bom.services.TarefaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tarefa")
@SecurityRequirement(name = "bearer-key")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("{usuarioId}")
    public ResponseEntity<?> buscarTarefas(
        @PathVariable("usuarioId") Long usuarioId,
        @PageableDefault(size = 100, sort = {"dataTarefa"}, direction = Sort.Direction.DESC) Pageable paginacao
    ) {
        return ResponseEntity.ok(tarefaService.buscarTarefas(usuarioId, paginacao));
    }

    @GetMapping("banda/{codigoBanda}")
    public ResponseEntity<?> buscarTarefasBanda(
        @PathVariable("codigoBanda") String codigoBanda,
        @PageableDefault(size = 100, sort = {"dataTarefa"}, direction = Sort.Direction.DESC) Pageable paginacao
    ) {
        return ResponseEntity.ok(tarefaService.buscarTarefasBanda(codigoBanda, paginacao));
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> concluirTarefa(@PathVariable("id") Long tarefaId) {
        return ResponseEntity.ok(tarefaService.concluirTarefa(tarefaId));
    }

    @PostMapping
    public ResponseEntity<?> criarTarefa(@RequestBody @Valid TarefaForm tarefaForm, UriComponentsBuilder uriBuilder) {
        final TarefaDTO tarefaDTO = tarefaService.criarTarefa(tarefaForm);
        return ResponseEntity.created(uriBuilder.path("/usuario/{id}").buildAndExpand(tarefaDTO.id()).toUri()).body(tarefaDTO);
    }

}
