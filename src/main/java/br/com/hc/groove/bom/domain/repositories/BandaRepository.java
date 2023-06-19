package br.com.hc.groove.bom.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hc.groove.bom.domain.models.entities.Banda;

import java.util.Optional;

public interface BandaRepository extends JpaRepository<Banda, Long>{

    Optional<Banda> findByCodigoAcesso(String codigo);
}
