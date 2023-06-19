package br.com.hc.groove.bom.domain.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaForm(
    @NotBlank
    String resposta,
    @NotNull
    Long usuarioId
) {
    
}
