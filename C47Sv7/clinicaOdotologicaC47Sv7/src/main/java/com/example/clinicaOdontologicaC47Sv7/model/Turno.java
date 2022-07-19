package com.example.clinicaOdontologicaC47Sv7.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@ToString
@Getter
@Setter
@Entity
@Table(name="turnos")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Turno {

    @Id
    @SequenceGenerator(name="turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Long idTurno;
    private Date fecha;
    private String hora;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) //si quiero que se borre el paciente al quitar un turno tendria que usar cascade
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnore
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "odontologo_id", nullable = false)
    @JsonIgnore
    private Odontologo odontologo;

    public Turno() {
    }


    public Turno(Date fecha, String hora, Paciente paciente, Odontologo odontologo) {
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Turno(Long idTurno, Date fecha, String hora, Paciente paciente, Odontologo odontologo) {
        this.idTurno = idTurno;
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }
}
