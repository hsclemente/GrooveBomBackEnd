package br.com.hc.groove.bom.domain.models.dtos;

import br.com.hc.groove.bom.domain.models.entities.Saldo;
import br.com.hc.groove.bom.domain.models.entities.Status;

public record SaldoBandaDTO(Status status, String descricao, Double valor, String usuario) {

    public SaldoBandaDTO(Saldo saldo) {
        this(saldo.getStatus(), saldo.getDescricao(), saldo.getValor(), saldo.getCodigoBanda());
    }
}
