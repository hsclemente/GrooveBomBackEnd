package br.com.hc.groove.bom.domain.models.dtos;

import java.time.LocalDateTime;

import br.com.hc.groove.bom.domain.models.entities.Resposta;

public record RespostaDTO(
    String resposta,
    UsuarioDTO usuarioResposta,
    LocalDateTime data
) {
    public RespostaDTO(Resposta resposta) {
        this(resposta.getResposta(), new UsuarioDTO(resposta.getUsuarioResposta()), resposta.getData());
    }
}
