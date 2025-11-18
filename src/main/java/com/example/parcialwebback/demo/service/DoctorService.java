package com.example.parcialwebback.demo.service;

import com.example.parcialwebback.demo.dto.DoctorDTO;
import com.example.parcialwebback.demo.exception.ResourceNotFoundException;
import com.example.parcialwebback.demo.mapper.DoctorMapper;
import com.example.parcialwebback.demo.model.Clinica;
import com.example.parcialwebback.demo.model.Doctor;
import com.example.parcialwebback.demo.repository.ClinicaRepository;
import com.example.parcialwebback.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ClinicaRepository clinicaRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepository doctorRepository, 
                        ClinicaRepository clinicaRepository,
                        DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.clinicaRepository = clinicaRepository;
        this.doctorMapper = doctorMapper;
    }

    @Transactional(readOnly = true)
    public List<DoctorDTO> findAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DoctorDTO findById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado con id: " + id));
        return doctorMapper.toDTO(doctor);
    }

    public DoctorDTO save(DoctorDTO doctorDTO) {
        Clinica clinica = clinicaRepository.findById(doctorDTO.getClinicaId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada con id: " + doctorDTO.getClinicaId()));
        
        Doctor doctor = doctorMapper.toEntity(doctorDTO);
        doctor.setClinica(clinica);
        
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(savedDoctor);
    }

    public DoctorDTO update(Long id, DoctorDTO doctorDTO) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado con id: " + id));
        
        Clinica clinica = clinicaRepository.findById(doctorDTO.getClinicaId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada con id: " + doctorDTO.getClinicaId()));
        
        existingDoctor.setNombre(doctorDTO.getNombre());
        existingDoctor.setEspecialidad(doctorDTO.getEspecialidad());
        existingDoctor.setClinica(clinica);
        existingDoctor.setEmail(doctorDTO.getEmail());
        existingDoctor.setTelefono(doctorDTO.getTelefono());
        existingDoctor.setFechaContratacion(doctorDTO.getFechaContratacion());
        
        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return doctorMapper.toDTO(updatedDoctor);
    }

    public void deleteById(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor no encontrado con id: " + id);
        }
        doctorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DoctorDTO> findByClinicaId(Long clinicaId) {
        return doctorRepository.findByClinicaId(clinicaId).stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DoctorDTO> findByEspecialidad(String especialidad) {
        return doctorRepository.findByEspecialidad(especialidad).stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DoctorDTO> findByNombre(String nombre) {
        return doctorRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }
}
