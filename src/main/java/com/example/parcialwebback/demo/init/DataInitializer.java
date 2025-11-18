package com.example.parcialwebback.demo.init;

import com.example.parcialwebback.demo.model.Clinica;
import com.example.parcialwebback.demo.repository.ClinicaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClinicaRepository clinicaRepository;

    public DataInitializer(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
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

        System.out.println("Datos de ejemplo cargados correctamente");
        System.out.println("Clinicas creadas: " + clinicaRepository.count());
    }
}
