package br.com.hc.groove.bom.services;


import br.com.hc.groove.bom.domain.models.dtos.BandaDTO;
import br.com.hc.groove.bom.domain.models.entities.Banda;
import br.com.hc.groove.bom.domain.models.entities.Usuario;
import br.com.hc.groove.bom.domain.models.forms.BandaForm;
import br.com.hc.groove.bom.domain.repositories.BandaRepository;
import br.com.hc.groove.bom.domain.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Optional;

@Service
public class BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<BandaDTO> buscarBandas(Pageable paginacao) {
        return bandaRepository.findAll(paginacao).map(BandaDTO::new);
    }

    public BandaDTO criarBanda(@Valid BandaForm banda) {
        final Optional<Banda> bandaOptional = bandaRepository.findByCodigoAcesso(banda.codigoAcesso());

        if (bandaOptional.isPresent()) {
            throw new KeyAlreadyExistsException();
        }

        final Banda savedBanda = bandaRepository.save(new Banda(banda));
        final Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Usuario user = usuarioRepository.findById(principal.getId()).orElseThrow(EntityNotFoundException::new);

        user.setBanda(savedBanda);
        usuarioRepository.save(user);

        return new BandaDTO(savedBanda);
    }

    public BandaDTO alterarNome(@Valid BandaForm bandaForm, Long bandaId) {
        Banda banda = bandaRepository.findById(bandaId).orElseThrow(EntityNotFoundException::new);
        banda.setNome(bandaForm.nome());
        
        return new BandaDTO(bandaRepository.save(banda));
    }
    
}
