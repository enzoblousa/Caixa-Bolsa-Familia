package com.caixa.beneficios_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "beneficiarios")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nis;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusBeneficiario status = StatusBeneficiario.ATIVO;

    public Beneficiario() {}

    public Beneficiario(String nis, String nome) {
        this.nis = nis;
        this.nome = nome;
        this.status = StatusBeneficiario.ATIVO;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNis() { return nis; }
    public void setNis(String nis) { this.nis = nis; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public StatusBeneficiario getStatus() { return status; }
    public void setStatus(StatusBeneficiario status) { this.status = status; }
}