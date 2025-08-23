package com.caixa.beneficios_api.controller;

import com.caixa.beneficios_api.dto.NovoBeneficiarioRequest;
import com.caixa.beneficios_api.dto.BeneficiarioResponse;
import com.caixa.beneficios_api.service.BeneficiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService service;

    public BeneficiarioController(BeneficiarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody NovoBeneficiarioRequest request) {
        try {
            BeneficiarioResponse beneficiarioSalvo = service.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"erro\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("{\"erro\": \"" + e.getMessage() + "\"}"); // ⚠️ EXPÕE DETALHES INTERNOS
}
    }

    @GetMapping
    public ResponseEntity<List<BeneficiarioResponse>> listarTodos() {
        List<BeneficiarioResponse> beneficiarios = service.listarTodos();
        return ResponseEntity.ok(beneficiarios);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<BeneficiarioResponse>> listarAtivos() {
        List<BeneficiarioResponse> beneficiariosAtivos = service.listarAtivos();
        return ResponseEntity.ok(beneficiariosAtivos);
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<BeneficiarioResponse>> listarInativos() {
        List<BeneficiarioResponse> beneficiariosInativos = service.listarInativos();
        return ResponseEntity.ok(beneficiariosInativos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficiarioResponse> buscarPorId(@PathVariable Long id) {
        BeneficiarioResponse beneficiario = service.buscarPorId(id);
        return ResponseEntity.ok(beneficiario);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        service.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}