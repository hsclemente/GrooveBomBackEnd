package br.com.hc.groove.bom.domain.models.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.hc.groove.bom.domain.models.entities.Faq;

public record FaqDTO(
    Long id,
    String pergunta,
    LocalDateTime data,
    List<RespostaDTO> respostas,
    UsuarioDTO usuarioFaq
) {
    public FaqDTO(Faq faq) {
        this(faq.getId(), faq.getPergunta(), faq.getData(), Objects.isNull(faq.getRespostas()) ? null :
                faq.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()), new UsuarioDTO(faq.getUsuarioFaq()));
    }
}