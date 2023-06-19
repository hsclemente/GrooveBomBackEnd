package br.com.hc.groove.bom.domain.models.filters;

import java.time.LocalDateTime;

public record FaqFilter(String nome, String usuarioNome, String bandaNome, LocalDateTime dataInicio, LocalDateTime dataFim) {}
