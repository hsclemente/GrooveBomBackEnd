package br.com.hc.groove.bom.domain.models.forms;

import jakarta.validation.constraints.NotBlank;

public record BandaForm(@NotBlank String nome, @NotBlank String codigoAcesso) {}
