package br.com.hc.groove.bom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hc.groove.bom.domain.models.dtos.FaqDTO;
import br.com.hc.groove.bom.domain.models.filters.FaqFilter;
import br.com.hc.groove.bom.domain.models.forms.FaqForm;
import br.com.hc.groove.bom.domain.models.forms.RespostaForm;
import br.com.hc.groove.bom.services.FaqService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("faq")
@SecurityRequirement(name = "bearer-key")
public class FaqController {
    
    @Autowired
    private FaqService faqService;

    @GetMapping("{id}")
    public ResponseEntity<?> buscarFaq(@PathVariable("id") Long id) {
        return ResponseEntity.ok(faqService.buscarFaq(id));
    }

    @PostMapping
    public ResponseEntity<?> criarFaq(@RequestBody @Valid FaqForm faqForm, UriComponentsBuilder uriBuilder) {
        FaqDTO faqDTO = faqService.criarFaq(faqForm);
        return ResponseEntity.created(uriBuilder.path("faq/{id}").buildAndExpand(faqDTO.id()).toUri()).body(faqDTO);    
    }

    @GetMapping
    public ResponseEntity<?> buscarFaqs(
        @RequestParam(name = "page_size", defaultValue = "50", required = false) int pageSize,
        @RequestParam(name = "page_index", defaultValue = "0", required = false) int pageIndex) {
        return ResponseEntity.ok(faqService.buscarFaqs(pageSize, pageIndex));
    }

    @PostMapping("responder/{faqId}")
    public ResponseEntity<?> responderFaq(@PathVariable("faqId") Long faqId, @RequestBody @Valid RespostaForm form, UriComponentsBuilder uriBuilder) {
        FaqDTO faqDTO = faqService.responderFaq(faqId, form);
        return ResponseEntity.created(uriBuilder.path("{faqId}").buildAndExpand(faqDTO.id()).toUri()).body(faqDTO);
    }
}
