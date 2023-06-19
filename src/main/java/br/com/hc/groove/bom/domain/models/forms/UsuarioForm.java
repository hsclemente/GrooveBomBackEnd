package br.com.hc.groove.bom.domain.models.forms;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioForm(
        @NotBlank
        String nome,
        String descricao,
        String especialidade,
        @NotBlank@Email
        String email,
        @NotBlank @JsonAlias("senha")
        String password
) {}