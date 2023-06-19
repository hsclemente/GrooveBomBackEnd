package br.com.hc.groove.bom.domain.repositories;

import br.com.hc.groove.bom.domain.models.entities.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Page<Tarefa> findByUsuarioId(Long usuarioId, Pageable pageable);
    Page<Tarefa> findByCodigoBanda(String usuarioId, Pageable pageable);
}
