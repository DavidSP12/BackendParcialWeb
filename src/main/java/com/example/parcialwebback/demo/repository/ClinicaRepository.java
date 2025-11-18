package com.example.parcialwebback.demo.repository;

import com.example.parcialwebback.demo.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    
    List<Clinica> findByCiudad(String ciudad);
    
    List<Clinica> findByNombreContainingIgnoreCase(String nombre);
}
