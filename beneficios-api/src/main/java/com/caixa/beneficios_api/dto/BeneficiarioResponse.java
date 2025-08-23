package com.caixa.beneficios_api.dto;

import com.caixa.beneficios_api.model.Beneficiario;
import com.caixa.beneficios_api.model.StatusBeneficiario;

public class BeneficiarioResponse {
    private Long id;
    private String nis;
    private String nome;
    private StatusBeneficiario status;

    public BeneficiarioResponse() {}

    public BeneficiarioResponse(Beneficiario beneficiario) {
        this.id = beneficiario.getId();
        this.nis = beneficiario.getNis();
        this.nome = beneficiario.getNome();
        this.status = beneficiario.getStatus();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNis() { return nis; }
    public void setNis(String nis) { this.nis = nis; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public StatusBeneficiario getStatus() { return status; }
    public void setStatus(StatusBeneficiario status) { this.status = status; }

    public static BeneficiarioResponse fromEntity(Beneficiario beneficiario) {
        return new BeneficiarioResponse(beneficiario);
    }
}