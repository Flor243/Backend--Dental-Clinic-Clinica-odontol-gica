package com.example.clinicaOdontologicaC47Sv7.repository;

import com.example.clinicaOdontologicaC47Sv7.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT o FROM Odontologo o where o.nombre =?1")
    Optional<Odontologo> findOdontologoByName(String name);
}
