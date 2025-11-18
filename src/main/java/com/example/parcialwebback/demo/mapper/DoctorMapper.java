package com.example.parcialwebback.demo.mapper;

import com.example.parcialwebback.demo.dto.DoctorDTO;
import com.example.parcialwebback.demo.model.Doctor;
import com.example.parcialwebback.demo.model.Clinica;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    private final ModelMapper modelMapper;

    public DoctorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DoctorDTO toDTO(Doctor doctor) {
        DoctorDTO dto = modelMapper.map(doctor, DoctorDTO.class);
        dto.setClinicaId(doctor.getClinica().getId());
        return dto;
    }

    public Doctor toEntity(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        // La clínica se asignará en el servicio
        return doctor;
    }
}
