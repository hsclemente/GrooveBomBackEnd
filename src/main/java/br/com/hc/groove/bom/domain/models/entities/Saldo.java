package br.com.hc.groove.bom.domain.models.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import br.com.hc.groove.bom.domain.models.forms.SaldoForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "gb_saldos")
@AllArgsConstructor@NoArgsConstructor
public class Saldo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "id_externo")
    private Long idExterno;

    @Column(name = "data_movimentacao")
    private LocalDateTime data;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "codigo_banda", nullable = false)
    private String codigoBanda;

    @PrePersist
    void create() {
        this.data = LocalDateTime.now();
    }

    public Saldo(SaldoForm saldoForm, Tipo tipo) {
        this.descricao = saldoForm.descricao();
        this.status = saldoForm.status();
        this.tipo = tipo;
        this.codigoBanda = saldoForm.codigoBanda();
        this.valor = BigDecimal.valueOf(saldoForm.valor()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.idExterno = saldoForm.idExterno();
    }
}
