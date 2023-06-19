package br.com.hc.groove.bom.services;

import br.com.hc.groove.bom.domain.models.dtos.CompromissoDTO;
import br.com.hc.groove.bom.domain.models.dtos.GraficoDTO;
import br.com.hc.groove.bom.domain.models.entities.Compromisso;
import br.com.hc.groove.bom.domain.models.forms.CompromissoForm;
import br.com.hc.groove.bom.domain.repositories.CompromissoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompromissoService {

    @Autowired
    private CompromissoRepository compromissoRepository;

    public List<CompromissoDTO> buscarCompromissos(Long destinatarioId, Integer pageSize, Integer pageIndex) {
        return compromissoRepository.buscarCompromissos(destinatarioId, pageSize, pageIndex).stream().sorted().map(CompromissoDTO::new).collect(Collectors.toList());
    }

    public CompromissoDTO criarComprimisso(@Valid CompromissoForm form) {
        return new CompromissoDTO(compromissoRepository.save(new Compromisso(form)));
    }

    public CompromissoDTO concluirCompromisso(Long compromissoId) {
        Compromisso compromisso = compromissoRepository.findById(compromissoId).orElseThrow(EntityNotFoundException::new);
        compromisso.setIsConcluido(true);
        return new CompromissoDTO(compromissoRepository.save(compromisso));
    }

    public String deletarCompromisso(Long compromissoId) {
        compromissoRepository.deleteById(compromissoId);
        return "Compromisso deletado com sucesso";
    }

    public List<GraficoDTO> buscarGraficos(Long destinatarioId) {
        final List<GraficoDTO> list = compromissoRepository.buscarGraficos(destinatarioId).stream().map(e -> {
            Long mes = ((Double) e[0]).longValue();
            Long qtd = (Long) e[1];
            return new GraficoDTO(mes, qtd);
        }).collect(Collectors.toList());

        return adicionarMesesVazios(list);
    }

    public List<GraficoDTO> buscarGraficosBanda(String codigoBanda) {
        final List<GraficoDTO> list = compromissoRepository.buscarGraficosBanda(codigoBanda).stream().map(e -> {
            Long mes = ((Double) e[0]).longValue();
            Long qtd = (Long) e[1];
            return new GraficoDTO(mes, qtd);
        }).collect(Collectors.toList());

        return adicionarMesesVazios(list);
    }

    private List<GraficoDTO> adicionarMesesVazios(List<GraficoDTO> list) {
        for (long i = 1; i < 13; i++) {
            if (!existeMes(list, i)) {
                adicionaMes(list, i);
            }
        }

        return list;
    }

    private boolean existeMes(List<GraficoDTO> list, Long mes) {
        return list.stream().anyMatch(el -> el.mes.equals(mes));
    }

    private List<GraficoDTO> adicionaMes(List<GraficoDTO> list, Long mes) {
        list.add(mes.intValue() - 1, new GraficoDTO(mes, 0L));
        return list;
    }

    public List<CompromissoDTO> buscarCompromissosBanda(String codigoBanda, int pageSize, int pageIndex) {
        return compromissoRepository.buscarCompromissosBanda(codigoBanda, pageSize, pageIndex).stream().sorted().map(CompromissoDTO::new).collect(Collectors.toList());
    }
}