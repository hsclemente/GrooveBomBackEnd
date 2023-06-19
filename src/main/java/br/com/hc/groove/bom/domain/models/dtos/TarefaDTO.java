package br.com.hc.groove.bom.domain.models.dtos;

import java.time.LocalDate;

import br.com.hc.groove.bom.domain.models.entities.Tarefa;

public record TarefaDTO(Long id, String titulo, String descricao, LocalDate dataTarefa, Boolean concluido) {
    public TarefaDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataTarefa(), tarefa.getConcluido());
    }
}
