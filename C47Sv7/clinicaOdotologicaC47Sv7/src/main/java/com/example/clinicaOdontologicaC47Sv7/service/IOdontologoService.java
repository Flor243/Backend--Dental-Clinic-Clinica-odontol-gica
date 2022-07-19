package com.example.clinicaOdontologicaC47Sv7.service;

import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.OdontologoDTO;

import java.util.Set;

public interface IOdontologoService {
    OdontologoDTO guardarOdontologo(OdontologoDTO odontologoDTO);
    OdontologoDTO buscar(Long id) throws ResourceNotFoundException;
    void actualizar(OdontologoDTO odontologoDTO) throws ResourceNotFoundException;
    void eliminar(Long id) throws ResourceNotFoundException;
    Set<OdontologoDTO> getTodos();
    OdontologoDTO buscarPorNombre(String name) throws ResourceNotFoundException;
}
