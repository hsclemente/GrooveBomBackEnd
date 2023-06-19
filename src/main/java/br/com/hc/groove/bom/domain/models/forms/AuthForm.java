package br.com.hc.groove.bom.domain.models.forms;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record AuthForm(@NotBlank @JsonAlias("email") String username, @NotBlank @JsonAlias("senha") String password){}
