package com.example.parcialwebback.demo.repository;

import com.example.parcialwebback.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    List<Doctor> findByClinicaId(Long clinicaId);
    
    List<Doctor> findByEspecialidad(String especialidad);
    
    List<Doctor> findByNombreContainingIgnoreCase(String nombre);
}
