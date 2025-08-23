package com.caixa.beneficios_api.dto;

public class NovoBeneficiarioRequest {
    private String nis;
    private String nome;

    public NovoBeneficiarioRequest() {}

    public NovoBeneficiarioRequest(String nis, String nome) {
        this.nis = nis;
        this.nome = nome;
    }

    public String getNis() { return nis; }
    public void setNis(String nis) { this.nis = nis; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}