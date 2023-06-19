package br.com.hc.groove.bom.domain.models.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hc.groove.bom.domain.models.forms.UsuarioForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "gb_usuarios")
@AllArgsConstructor@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "nome", nullable = true)
    private String nome;

    @Column(name = "descricao", nullable = true, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "especialidade", nullable = true)
    private String especialidade;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "ativo", nullable = true, columnDefinition = "BOOLEAN default true")
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banda")
    private Banda banda;

    @Column(name = "password")
    private String password;

    @PrePersist
    public void create() {
        this.id = null;
        this.ativo = true;
    }

    public Usuario(UsuarioForm usuarioForm) {
        this.nome = usuarioForm.nome(); 
        this.descricao = usuarioForm.descricao();
        this.especialidade = usuarioForm.especialidade();
        this.email = usuarioForm.email(); 
        this.password = usuarioForm.password();
    } 

    public Usuario put(UsuarioForm usuarioDTO) {
        if (usuarioDTO.descricao() != null) {
            this.descricao = usuarioDTO.descricao();
        }

        if (usuarioDTO.email() != null && usuarioDTO.email().matches("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})")) {
            this.email = usuarioDTO.email();
        }

        if (usuarioDTO.especialidade() != null) {
            this.especialidade = usuarioDTO.especialidade();
        }

        if (usuarioDTO.nome() != null) {
            this.nome = usuarioDTO.nome();
        }

        if(usuarioDTO.password() != null) {
            this.password = usuarioDTO.password();
        }

        return this;
    } 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
