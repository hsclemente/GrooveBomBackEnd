package br.com.hc.groove.bom.services;

import br.com.hc.groove.bom.domain.models.dtos.TarefaDTO;
import br.com.hc.groove.bom.domain.models.entities.Tarefa;
import br.com.hc.groove.bom.domain.models.forms.TarefaForm;
import br.com.hc.groove.bom.domain.repositories.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<TarefaDTO> buscarTarefas(Long usuarioId, Pageable paginacao) {
        return tarefaRepository.findByUsuarioId(usuarioId, paginacao).stream().sorted()
                .map(TarefaDTO::new)
                .collect(Collectors.toList());
    }

    public TarefaDTO criarTarefa(TarefaForm tarefaForm) {
        return new TarefaDTO(tarefaRepository.save(new Tarefa(tarefaForm)));
    }

    public TarefaDTO concluirTarefa(Long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(EntityNotFoundException::new);
        tarefa.setConcluido(true);
        return new TarefaDTO(tarefaRepository.save(tarefa));
    }

    public List<TarefaDTO> buscarTarefasBanda(String codigoBanda, Pageable paginacao) {
        return tarefaRepository.findByCodigoBanda(codigoBanda, paginacao).stream()
                .sorted()
                .map(TarefaDTO::new)
                .collect(Collectors.toList());
    }
}
