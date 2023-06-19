package br.com.hc.groove.bom.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hc.groove.bom.domain.models.entities.Saldo;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long>{
    Page<Saldo> findAllByIdExterno(Long idExterno, Pageable pageable);
    Page<Saldo> findAllByCodigoBanda(String codigoBanda, Pageable pageable);
}
