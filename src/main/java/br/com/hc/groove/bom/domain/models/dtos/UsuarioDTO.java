package br.com.hc.groove.bom.domain.models.dtos;

import br.com.hc.groove.bom.domain.models.entities.Usuario;
public record UsuarioDTO (
    Long id,
    String nome,
    String descricao,
    String especialidade,
    String email
){
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getDescricao(), usuario.getEspecialidade(), usuario.getEmail());
    } 
}
