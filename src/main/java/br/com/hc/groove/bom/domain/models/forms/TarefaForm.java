package br.com.hc.groove.bom.domain.models.forms;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefaForm(@NotBlank String titulo, @NotBlank String descricao, @NotNull LocalDate dataTarefa, Long usuarioId, String codigoBanda) {
}
