package br.com.hc.groove.bom.controllers;

import br.com.hc.groove.bom.domain.models.entities.Usuario;
import br.com.hc.groove.bom.domain.models.forms.AuthForm;
import br.com.hc.groove.bom.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody@Valid AuthForm form) {
        final Usuario principal = (Usuario) manager.authenticate(new UsernamePasswordAuthenticationToken(form.username(), form.password())).getPrincipal();
        return ResponseEntity.ok("""
            {
                "id" : "%s",
                "nome" : "%s",
                "descricao": "%s",
                "especialidade" : "%s",
                "email" : "%s",
                "codigoBanda" : "%s",
                "token" : "%s"
            }""".formatted(principal.getId(), 
                           principal.getNome(),
                           principal.getDescricao(), 
                           principal.getEspecialidade(),
                           principal.getEmail(),
                           principal.getBanda() != null ?  principal.getBanda().getCodigoAcesso() : "null",
                           tokenService.tokenFactory(principal)
            ));
    }
}
