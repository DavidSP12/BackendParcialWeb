package com.example.parcialwebback.demo.controller;

import com.example.parcialwebback.demo.dto.ClinicaDTO;
import com.example.parcialwebback.demo.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicas")
public class ClinicaRestController {

    private final ClinicaService clinicaService;

    public ClinicaRestController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping
    public ResponseEntity<List<ClinicaDTO>> getAllClinicas() {
        List<ClinicaDTO> clinicas = clinicaService.findAll();
        return ResponseEntity.ok(clinicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDTO> getClinicaById(@PathVariable Long id) {
        ClinicaDTO clinica = clinicaService.findById(id);
        return ResponseEntity.ok(clinica);
    }

    @PostMapping
    public ResponseEntity<ClinicaDTO> createClinica(@Valid @RequestBody ClinicaDTO clinicaDTO) {
        ClinicaDTO savedClinica = clinicaService.save(clinicaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaDTO> updateClinica(@PathVariable Long id, 
                                                    @Valid @RequestBody ClinicaDTO clinicaDTO) {
        ClinicaDTO updatedClinica = clinicaService.update(id, clinicaDTO);
        return ResponseEntity.ok(updatedClinica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinica(@PathVariable Long id) {
        clinicaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<ClinicaDTO>> getClinicasByCiudad(@PathVariable String ciudad) {
        List<ClinicaDTO> clinicas = clinicaService.findByCiudad(ciudad);
        return ResponseEntity.ok(clinicas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClinicaDTO>> buscarClinicasPorNombre(@RequestParam String nombre) {
        List<ClinicaDTO> clinicas = clinicaService.findByNombre(nombre);
        return ResponseEntity.ok(clinicas);
    }
}
