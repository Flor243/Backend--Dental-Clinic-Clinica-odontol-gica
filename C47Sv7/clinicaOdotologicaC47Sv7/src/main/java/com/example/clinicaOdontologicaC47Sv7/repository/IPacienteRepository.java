package com.example.clinicaOdontologicaC47Sv7.repository;

import com.example.clinicaOdontologicaC47Sv7.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p where p.nombre =?1")
    Optional<Paciente> findPacienteByName(String name);
}

