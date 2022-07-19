package com.example.clinicaOdontologicaC47Sv7.repository;

import com.example.clinicaOdontologicaC47Sv7.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {

}