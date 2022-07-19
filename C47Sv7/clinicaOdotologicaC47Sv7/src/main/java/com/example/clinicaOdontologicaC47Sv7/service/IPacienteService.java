package com.example.clinicaOdontologicaC47Sv7.service;

import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.PacienteDTO;

import java.util.Set;

public interface IPacienteService {
    PacienteDTO guardarPaciente(PacienteDTO pacienteDTO);
    PacienteDTO buscar(Long id) throws ResourceNotFoundException;
    void actualizar(PacienteDTO pacienteDTO) throws ResourceNotFoundException;
    void eliminar(Long id) throws ResourceNotFoundException;
    Set<PacienteDTO> getTodos();
    PacienteDTO buscarPorNombre(String name) throws ResourceNotFoundException;

}
