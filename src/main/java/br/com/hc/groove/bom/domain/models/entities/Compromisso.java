package br.com.hc.groove.bom.domain.models.entities;

import java.time.LocalDateTime;

import br.com.hc.groove.bom.domain.models.forms.CompromissoForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "gb_compromissos")
@AllArgsConstructor@NoArgsConstructor
public class Compromisso implements Comparable<Compromisso> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_destinatario")
    private Long idDestinatario;

    @Column(name = "is_show")
    private Boolean isShow;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "desc_compromisso", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "is_concluido")
    private Boolean isConcluido;

    @Column(name = "fk_codigo_banda")
    private String codigoBanda;

    public Compromisso(CompromissoForm form) {
        this.isShow = form.isShow();
        this.descricao = form.descricao();
        this.data = form.data();
        this.idDestinatario = form.idDestinatario();
        this.codigoBanda = form.codigoBanda();
    }

    @Override
    public int compareTo(Compromisso o) {
        LocalDateTime now = LocalDateTime.now();
        long diffThis = Math.abs(this.data.toLocalDate().toEpochDay() - now.toLocalDate().toEpochDay());
        long diffOutro = Math.abs(o.getData().toLocalDate().toEpochDay() - now.toLocalDate().toEpochDay());
        return Long.compare(diffThis, diffOutro);
    }
}