package com.example.parcialwebback.demo.service;

import com.example.parcialwebback.demo.dto.ClinicaDTO;
import com.example.parcialwebback.demo.exception.ResourceNotFoundException;
import com.example.parcialwebback.demo.mapper.ClinicaMapper;
import com.example.parcialwebback.demo.model.Clinica;
import com.example.parcialwebback.demo.repository.ClinicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;
    private final ClinicaMapper clinicaMapper;

    public ClinicaService(ClinicaRepository clinicaRepository, ClinicaMapper clinicaMapper) {
        this.clinicaRepository = clinicaRepository;
        this.clinicaMapper = clinicaMapper;
    }

    @Transactional(readOnly = true)
    public List<ClinicaDTO> findAll() {
        return clinicaRepository.findAll().stream()
                .map(clinicaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClinicaDTO findById(Long id) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada con id: " + id));
        return clinicaMapper.toDTO(clinica);
    }

    public ClinicaDTO save(ClinicaDTO clinicaDTO) {
        Clinica clinica = clinicaMapper.toEntity(clinicaDTO);
        Clinica savedClinica = clinicaRepository.save(clinica);
        return clinicaMapper.toDTO(savedClinica);
    }

    public ClinicaDTO update(Long id, ClinicaDTO clinicaDTO) {
        Clinica existingClinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada con id: " + id));
        
        existingClinica.setNombre(clinicaDTO.getNombre());
        existingClinica.setDireccion(clinicaDTO.getDireccion());
        existingClinica.setCantidadCamas(clinicaDTO.getCantidadCamas());
        existingClinica.setTelefono(clinicaDTO.getTelefono());
        existingClinica.setCiudad(clinicaDTO.getCiudad());
        
        Clinica updatedClinica = clinicaRepository.save(existingClinica);
        return clinicaMapper.toDTO(updatedClinica);
    }

    public void deleteById(Long id) {
        if (!clinicaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Clínica no encontrada con id: " + id);
        }
        clinicaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ClinicaDTO> findByCiudad(String ciudad) {
        return clinicaRepository.findByCiudad(ciudad).stream()
                .map(clinicaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClinicaDTO> findByNombre(String nombre) {
        return clinicaRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(clinicaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
