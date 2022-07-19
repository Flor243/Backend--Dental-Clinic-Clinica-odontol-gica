package com.example.clinicaOdontologicaC47Sv7.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private int dni;
    private Date fechaIngreso;
    private DomicilioDTO domicilioDTO;

    public PacienteDTO() {
    }
}
