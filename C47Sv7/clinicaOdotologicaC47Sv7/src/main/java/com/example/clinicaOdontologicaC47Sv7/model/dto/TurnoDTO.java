package com.example.clinicaOdontologicaC47Sv7.model.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class TurnoDTO {
    private Long idTurno;
    private Date fecha;
    private String hora;
    private PacienteDTO pacienteDTO;
    private OdontologoDTO odontologoDTO;

    public TurnoDTO() {
    }
}
