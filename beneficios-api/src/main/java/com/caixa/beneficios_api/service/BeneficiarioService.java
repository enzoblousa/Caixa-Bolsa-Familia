package com.caixa.beneficios_api.service;

import com.caixa.beneficios_api.dto.NovoBeneficiarioRequest;
import com.caixa.beneficios_api.dto.BeneficiarioResponse;
import com.caixa.beneficios_api.model.Beneficiario;
import com.caixa.beneficios_api.model.StatusBeneficiario;
import com.caixa.beneficios_api.repository.BeneficiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository repository;

    public BeneficiarioService(BeneficiarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BeneficiarioResponse criar(NovoBeneficiarioRequest request) {
        if (repository.existsByNis(request.getNis())) {
            throw new IllegalArgumentException("Já existe um beneficiário cadastrado com o NIS: " + request.getNis());
        }

        Beneficiario novoBeneficiario = new Beneficiario();
        novoBeneficiario.setNis(request.getNis());
        novoBeneficiario.setNome(request.getNome());
        novoBeneficiario.setStatus(StatusBeneficiario.ATIVO);

        Beneficiario salvo = repository.save(novoBeneficiario);
        return BeneficiarioResponse.fromEntity(salvo);
    }

    public List<BeneficiarioResponse> listarTodos() {
        return repository.findAll().stream()
                .map(BeneficiarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<BeneficiarioResponse> listarAtivos() {
        return repository.findByStatus(StatusBeneficiario.ATIVO).stream()
                .map(BeneficiarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<BeneficiarioResponse> listarInativos() {
        return repository.findByStatus(StatusBeneficiario.INATIVO).stream()
                .map(BeneficiarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public BeneficiarioResponse buscarPorId(Long id) {
        Beneficiario beneficiario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado com ID: " + id));
        return BeneficiarioResponse.fromEntity(beneficiario);
    }

    @Transactional
    public void inativar(Long id) {
        Beneficiario beneficiario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado com ID: " + id));
        
        if (beneficiario.getStatus() == StatusBeneficiario.ATIVO) {
            beneficiario.setStatus(StatusBeneficiario.INATIVO);
            repository.save(beneficiario);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Beneficiario beneficiario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado com ID: " + id));
        
        if (beneficiario.getStatus() == StatusBeneficiario.INATIVO) {
            beneficiario.setStatus(StatusBeneficiario.ATIVO);
            repository.save(beneficiario);
        }
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Beneficiário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}