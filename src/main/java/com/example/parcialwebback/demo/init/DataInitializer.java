package com.example.parcialwebback.demo.init;

import com.example.parcialwebback.demo.model.Clinica;
import com.example.parcialwebback.demo.model.Doctor;
import com.example.parcialwebback.demo.repository.ClinicaRepository;
import com.example.parcialwebback.demo.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClinicaRepository clinicaRepository;
    private final DoctorRepository doctorRepository;

    public DataInitializer(ClinicaRepository clinicaRepository, DoctorRepository doctorRepository) {
        this.clinicaRepository = clinicaRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear clínicas de ejemplo
        Clinica clinica1 = Clinica.builder()
                .nombre("Clínica San Rafael")
                .direccion("Av. Principal 123")
                .cantidadCamas(50)
                .telefono("555-0001")
                .ciudad("Bogotá")
                .build();
        clinica1 = clinicaRepository.save(clinica1);

        Clinica clinica2 = Clinica.builder()
                .nombre("Centro Médico El Bosque")
                .direccion("Calle 45 #23-67")
                .cantidadCamas(30)
                .telefono("555-0002")
                .ciudad("Medellín")
                .build();
        clinica2 = clinicaRepository.save(clinica2);

        Clinica clinica3 = Clinica.builder()
                .nombre("Hospital Universitario")
                .direccion("Carrera 10 #15-30")
                .cantidadCamas(100)
                .telefono("555-0003")
                .ciudad("Cali")
                .build();
        clinica3 = clinicaRepository.save(clinica3);

        // Crear doctores de ejemplo
        Doctor doctor1 = Doctor.builder()
                .nombre("Dr. Juan Pérez")
                .especialidad("Cardiología")
                .clinica(clinica1)
                .email("juan.perez@clinica.com")
                .telefono("555-1001")
                .fechaContratacion(LocalDate.of(2020, 1, 15))
                .build();
        doctorRepository.save(doctor1);

        Doctor doctor2 = Doctor.builder()
                .nombre("Dra. María García")
                .especialidad("Pediatría")
                .clinica(clinica1)
                .email("maria.garcia@clinica.com")
                .telefono("555-1002")
                .fechaContratacion(LocalDate.of(2019, 5, 20))
                .build();
        doctorRepository.save(doctor2);

        Doctor doctor3 = Doctor.builder()
                .nombre("Dr. Carlos Rodríguez")
                .especialidad("Neurología")
                .clinica(clinica2)
                .email("carlos.rodriguez@clinica.com")
                .telefono("555-1003")
                .fechaContratacion(LocalDate.of(2021, 3, 10))
                .build();
        doctorRepository.save(doctor3);

        Doctor doctor4 = Doctor.builder()
                .nombre("Dra. Ana Martínez")
                .especialidad("Dermatología")
                .clinica(clinica2)
                .email("ana.martinez@clinica.com")
                .telefono("555-1004")
                .fechaContratacion(LocalDate.of(2022, 7, 1))
                .build();
        doctorRepository.save(doctor4);

        Doctor doctor5 = Doctor.builder()
                .nombre("Dr. Luis Fernández")
                .especialidad("Ortopedia")
                .clinica(clinica3)
                .email("luis.fernandez@clinica.com")
                .telefono("555-1005")
                .fechaContratacion(LocalDate.of(2018, 11, 15))
                .build();
        doctorRepository.save(doctor5);

        System.out.println("Datos de ejemplo cargados correctamente");
        System.out.println("Clinicas creadas: " + clinicaRepository.count());
        System.out.println("Doctores creados: " + doctorRepository.count());
    }
}
