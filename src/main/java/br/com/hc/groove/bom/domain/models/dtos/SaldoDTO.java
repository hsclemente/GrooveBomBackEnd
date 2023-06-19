package br.com.hc.groove.bom.domain.models.dtos;

import br.com.hc.groove.bom.domain.models.entities.Saldo;
import br.com.hc.groove.bom.domain.models.entities.Status;

public record SaldoDTO(Status status, String descricao, Double valor) {

    public SaldoDTO(Saldo saldo) {
        this(saldo.getStatus(), saldo.getDescricao(), saldo.getValor()  );
    }
}
