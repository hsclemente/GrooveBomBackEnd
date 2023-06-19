package br.com.hc.groove.bom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hc.groove.bom.domain.models.dtos.UsuarioDTO;
import br.com.hc.groove.bom.domain.models.forms.UsuarioForm;
import br.com.hc.groove.bom.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PatchMapping("{usuarioId}/banda/{codigoBanda}")
    public ResponseEntity<?> adicionarBanda(@PathVariable("usuarioId") Long usuarioId, @PathVariable("codigoBanda") String codigoBanda) {
        return ResponseEntity.ok(usuarioService.adicionarBanda(usuarioId, codigoBanda));
    }

    @DeleteMapping("{usuarioId}/banda")
    public ResponseEntity<?> removerBanda(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok(usuarioService.removerBanda(usuarioId));
    }

    @GetMapping("banda/{codigoBanda}")
    public ResponseEntity<?> buscarUsuariosDaBanda(@PathVariable("codigoBanda") String codigoBanda) {
        return ResponseEntity.ok(usuarioService.buscarUsuariosDaBanda(codigoBanda));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("id") Long usuarioId) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioForm usuario, UriComponentsBuilder uriBuilder) {
        UsuarioDTO usuarioDTO = usuarioService.criarUsuario(usuario);
        return ResponseEntity.created(uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioDTO.id()).toUri()).body(usuarioDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioForm usuario, @PathVariable("id") Long usuarioId) {
        return ResponseEntity.ok(usuarioService.alterarUsuario(usuario, usuarioId));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> desativarUsuario(@PathVariable("id") Long usuarioId) {
        return ResponseEntity.ok(usuarioService.desativarUsuario(usuarioId));
    }
}
