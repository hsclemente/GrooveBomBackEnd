package br.com.hc.groove.bom.domain.models.forms;

import br.com.hc.groove.bom.domain.models.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaldoForm(@NotNull Double valor, @NotBlank String descricao, @NotNull Long idExterno, @NotNull Status status, String codigoBanda) {}
