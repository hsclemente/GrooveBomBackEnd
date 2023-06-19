package br.com.hc.groove.bom.services;

import br.com.hc.groove.bom.domain.models.dtos.SaldoBandaDTO;
import br.com.hc.groove.bom.domain.models.dtos.SaldoDTO;
import br.com.hc.groove.bom.domain.models.entities.Saldo;
import br.com.hc.groove.bom.domain.models.entities.Tipo;
import br.com.hc.groove.bom.domain.models.forms.SaldoForm;
import br.com.hc.groove.bom.domain.repositories.SaldoRepository;
import br.com.hc.groove.bom.domain.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {
    @Autowired
    private SaldoRepository saldoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<SaldoDTO> buscarSaldos(Long idExterno, Pageable paginacao) {
        return saldoRepository.findAllByIdExterno(idExterno, paginacao).map(SaldoDTO::new);
    }

    public Long criarSaldo(@Valid SaldoForm form) {
        return saldoRepository.save(new Saldo(form, Tipo.USUARIO)).getId();
    }

    public Long criarSaldoBanda(@Valid SaldoForm form) {
        return saldoRepository.save(new Saldo(form, Tipo.BANDA)).getId();
    }

    public Page<SaldoBandaDTO> buscarSaldosBanda(String codigoBanda, Pageable paginacao) {
        return saldoRepository.findAllByCodigoBanda(codigoBanda, paginacao)
                .map(saldo -> {
                        saldo.setCodigoBanda(usuarioRepository.findById(saldo.getIdExterno()).orElseThrow(EntityNotFoundException::new).getNome());
                        return new SaldoBandaDTO(saldo);
                });
    }
}
