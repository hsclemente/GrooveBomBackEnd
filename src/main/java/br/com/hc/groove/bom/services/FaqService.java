package br.com.hc.groove.bom.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hc.groove.bom.domain.models.dtos.FaqDTO;
import br.com.hc.groove.bom.domain.models.entities.Faq;
import br.com.hc.groove.bom.domain.models.entities.Resposta;
import br.com.hc.groove.bom.domain.models.filters.FaqFilter;
import br.com.hc.groove.bom.domain.models.forms.FaqForm;
import br.com.hc.groove.bom.domain.models.forms.RespostaForm;
import br.com.hc.groove.bom.domain.repositories.FaqRepository;
import br.com.hc.groove.bom.domain.repositories.RespostaRepository;
import br.com.hc.groove.bom.domain.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class FaqService {
 
    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public FaqDTO buscarFaq(Long id) {
        return new FaqDTO(faqRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public FaqDTO criarFaq(@Valid FaqForm faqForm) {
        return new FaqDTO(faqRepository.save(new Faq(faqForm, usuarioRepository.findById(faqForm.usuarioId()).orElseThrow(EntityNotFoundException::new))));
    }

    public List<FaqDTO> buscarFaqs(int pageSize, int pageIndex) {
        return faqRepository.buscarFaqs(pageSize, pageIndex).stream().map(FaqDTO::new).collect(Collectors.toList());
    }

    public FaqDTO responderFaq(Long faqId, @Valid RespostaForm form) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(EntityNotFoundException::new);
        faq.getRespostas().add(respostaRepository.save(new Resposta(form, usuarioRepository.findById(form.usuarioId()).orElseThrow(EntityNotFoundException::new))));

        return new FaqDTO(faqRepository.save(faq));
    }
}
