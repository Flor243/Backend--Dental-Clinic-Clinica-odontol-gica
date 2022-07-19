package com.example.clinicaOdontologicaC47Sv7.service;

import com.example.clinicaOdontologicaC47Sv7.exceptions.BadRequestException;
import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.TurnoDTO;

import java.util.List;

public interface ITurnoService {
    TurnoDTO guardarTurno(TurnoDTO turnoDTO) throws BadRequestException;
    TurnoDTO buscar(Long id) throws ResourceNotFoundException;
    void actualizar(TurnoDTO turnoDTO) throws ResourceNotFoundException;
    void eliminar(Long id) throws ResourceNotFoundException;
    List<TurnoDTO> getTodos();
}
