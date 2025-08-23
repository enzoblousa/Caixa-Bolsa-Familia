package com.caixa.beneficios_api.repository;

import com.caixa.beneficios_api.model.Beneficiario;
import com.caixa.beneficios_api.model.StatusBeneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
    Optional<Beneficiario> findByNis(String nis);
    List<Beneficiario> findByStatus(StatusBeneficiario status);
    boolean existsByNis(String nis);
}