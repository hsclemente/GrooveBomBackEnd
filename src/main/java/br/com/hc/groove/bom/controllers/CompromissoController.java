package br.com.hc.groove.bom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hc.groove.bom.domain.models.dtos.CompromissoDTO;
import br.com.hc.groove.bom.domain.models.forms.CompromissoForm;
import br.com.hc.groove.bom.services.CompromissoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/compromisso")
@SecurityRequirement(name = "bearer-key")
public class CompromissoController {

    @Autowired
    private CompromissoService compromissoService;

    @GetMapping("{destinatarioId}")
    public ResponseEntity<?> buscarCompromissos(
            @PathVariable("destinatarioId") Long destinatarioId,
            @RequestParam(name = "page_size", defaultValue = "100", required = false) int pageSize,
            @RequestParam(name = "page_index", defaultValue = "0", required = false) int pageIndex
    ) {
        return ResponseEntity.ok(compromissoService.buscarCompromissos(destinatarioId, pageSize, pageIndex));
    }

    @GetMapping("/banda/{codigoBanda}")
    public ResponseEntity<?> buscarCompromissos(
            @PathVariable("codigoBanda") String codigoBanda,
            @RequestParam(name = "page_size", defaultValue = "100", required = false) int pageSize,
            @RequestParam(name = "page_index", defaultValue = "0", required = false) int pageIndex
    ) {
        return ResponseEntity.ok(compromissoService.buscarCompromissosBanda(codigoBanda, pageSize, pageIndex));
    }

    @GetMapping("grafico/{destinatarioId}")
    public ResponseEntity<?> buscarGraficos(@PathVariable("destinatarioId") Long destinatarioId){
        return ResponseEntity.ok(compromissoService.buscarGraficos(destinatarioId));
    }

    @GetMapping("grafico/banda/{codigoBanda}")
    public ResponseEntity<?> buscarGraficosBanda(@PathVariable("codigoBanda") String codigoBanda){
        return ResponseEntity.ok(compromissoService.buscarGraficosBanda(codigoBanda));
    }

    @PostMapping
    public ResponseEntity<?> criarCompromisso(@RequestBody@Valid CompromissoForm form, UriComponentsBuilder uriBuilder) {
        CompromissoDTO CompromissoDTO = compromissoService.criarComprimisso(form);
        return ResponseEntity.created(uriBuilder.path("/compromisso/{idDestinatario}").buildAndExpand(CompromissoDTO.idDestinatario()).toUri()).body(CompromissoDTO);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> concluirTarefa(@PathVariable("id") Long compromissoId) {
        return ResponseEntity.ok(compromissoService.concluirCompromisso(compromissoId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarCompromisso(@PathVariable("id") Long compromissoId) {
        return ResponseEntity.ok(compromissoService.deletarCompromisso(compromissoId));
    }

}