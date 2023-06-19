package br.com.hc.groove.bom.domain.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FaqForm(
    @NotBlank
    String pergunta,
    @NotNull
    Long usuarioId
) {
    
}
