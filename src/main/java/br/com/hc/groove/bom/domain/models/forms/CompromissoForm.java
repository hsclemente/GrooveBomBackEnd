package br.com.hc.groove.bom.domain.models.forms;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record CompromissoForm(@NotNull Boolean isShow, String descricao, @NotNull LocalDateTime data, Long idDestinatario, String codigoBanda) {}