package br.com.hc.groove.bom.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hc.groove.bom.domain.models.entities.Banda;
import br.com.hc.groove.bom.domain.models.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdAndAtivoIsTrue(Long id);

    List<Usuario> findAllByBanda(Banda orElseThrow);

    UserDetails findByEmail(String email);
}
