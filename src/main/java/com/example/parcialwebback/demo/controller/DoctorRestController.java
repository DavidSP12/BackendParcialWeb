package com.example.parcialwebback.demo.controller;

import com.example.parcialwebback.demo.dto.DoctorDTO;
import com.example.parcialwebback.demo.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorRestController {

    private final DoctorService doctorService;

    public DoctorRestController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctores() {
        List<DoctorDTO> doctores = doctorService.findAll();
        return ResponseEntity.ok(doctores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        DoctorDTO doctor = doctorService.findById(id);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO savedDoctor = doctorService.save(doctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, 
                                                   @Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO updatedDoctor = doctorService.update(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clinica/{clinicaId}")
    public ResponseEntity<List<DoctorDTO>> getDoctoresByClinica(@PathVariable Long clinicaId) {
        List<DoctorDTO> doctores = doctorService.findByClinicaId(clinicaId);
        return ResponseEntity.ok(doctores);
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<DoctorDTO>> getDoctoresByEspecialidad(@PathVariable String especialidad) {
        List<DoctorDTO> doctores = doctorService.findByEspecialidad(especialidad);
        return ResponseEntity.ok(doctores);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DoctorDTO>> buscarDoctoresPorNombre(@RequestParam String nombre) {
        List<DoctorDTO> doctores = doctorService.findByNombre(nombre);
        return ResponseEntity.ok(doctores);
    }
}
