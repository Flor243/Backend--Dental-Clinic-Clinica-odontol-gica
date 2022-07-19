package com.example.clinicaOdontologicaC47Sv7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table(name="odontologos")
public class Odontologo {

    @Id
    @SequenceGenerator(name="odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1, initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    private Long id;
    private String nombre;
    private String apellido;
    private int matricula;
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo(){

    }

    public Odontologo(String nombre, String apellido, int matricula, Set<Turno> turnos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.turnos = turnos;
    }

    public Odontologo(Long id, String nombre, String apellido, int matricula, Set<Turno> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.turnos = turnos;
    }
}
