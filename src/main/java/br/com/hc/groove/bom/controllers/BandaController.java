package br.com.hc.groove.bom.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hc.groove.bom.domain.models.dtos.BandaDTO;
import br.com.hc.groove.bom.domain.models.forms.BandaForm;
import br.com.hc.groove.bom.services.BandaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/banda")
@SecurityRequirement(name = "bearer-key")
public class BandaController {
    @Autowired
    private BandaService bandaService;

    @GetMapping
    public ResponseEntity<?> buscarBandas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(bandaService.buscarBandas(paginacao));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> criarBanda(@RequestBody@Valid BandaForm banda, UriComponentsBuilder uriBuilder) throws Exception {
        BandaDTO bandaDTO = bandaService.criarBanda(banda);
        return ResponseEntity.created(uriBuilder.path("banda/{id}").buildAndExpand(bandaDTO.id()).toUri()).body(bandaDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterarNome(@RequestBody @Valid BandaForm banda, @PathVariable("id")Long bandaId) {
        return ResponseEntity.ok(bandaService.alterarNome(banda, bandaId));
    }
}
