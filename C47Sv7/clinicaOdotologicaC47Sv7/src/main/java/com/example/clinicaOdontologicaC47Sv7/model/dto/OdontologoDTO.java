package com.example.clinicaOdontologicaC47Sv7.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdontologoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private int matricula;

    public OdontologoDTO() {
    }
}
