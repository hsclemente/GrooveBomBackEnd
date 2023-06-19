package br.com.hc.groove.bom.domain.models.entities;

import br.com.hc.groove.bom.domain.models.forms.TarefaForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "gb_tarefas")
@AllArgsConstructor@NoArgsConstructor
public class Tarefa implements Comparable<Tarefa> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_tarefa")
    private LocalDate dataTarefa;

    @Column(name = "is_concluido")
    private Boolean concluido;

    @Column(name = "fk_usuario_id")
    private Long usuarioId;

    @Column(name = "fk_codigo_banda")
    private String codigoBanda;

    @PrePersist
    void create() {
        this.concluido = false;
    }

    public Tarefa(TarefaForm form) {
        this.titulo = form.titulo();
        this.descricao = form.descricao();
        this.dataTarefa = form.dataTarefa();
        this.usuarioId = form.usuarioId();
        this.codigoBanda = form.codigoBanda();
    }

    @Override
    public int compareTo(Tarefa o) {
        if (this.concluido && !o.concluido) {
            return 1; // this "true" fica depois de outraClasse "false"
        } else if (!this.concluido && o.concluido) {
            return -1; // this "false" fica antes de outraClasse "true"
        } else {
            return 0; // mantém a ordem original
        }
    }
}
