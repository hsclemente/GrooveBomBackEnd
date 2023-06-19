package br.com.hc.groove.bom.domain.models.entities;

import java.time.LocalDateTime;
import java.util.List;

import br.com.hc.groove.bom.domain.models.forms.FaqForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "gb_faqs")
@AllArgsConstructor@NoArgsConstructor
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "pergunta", nullable = true, columnDefinition = "TEXT")
    private String pergunta;

    @Column(name = "data", nullable = true)
    private LocalDateTime  data;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "respostas")
    private List<Resposta> respostas;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_faq")
    private Usuario usuarioFaq;

    @PrePersist
    public void abertura() {
        this.data = LocalDateTime.now();
    }

    public Faq(FaqForm faqForm, Usuario usuario) {
        this.pergunta = faqForm.pergunta();
        this.usuarioFaq = usuario;
    }
}
