package br.com.hc.groove.bom.domain.models.dtos;

import br.com.hc.groove.bom.domain.models.entities.Compromisso;

import java.time.LocalDateTime;

public record CompromissoDTO(
        Long id,
        Boolean isShow,
        LocalDateTime data,
        String descricao,
        Boolean isConcluido,
        Long idDestinatario
) {
    public CompromissoDTO(Compromisso compromisso) {
        this(compromisso.getId(), compromisso.getIsShow(), compromisso.getData(), compromisso.getDescricao(),  compromisso.getIsConcluido(), compromisso.getIdDestinatario());
    }
}
