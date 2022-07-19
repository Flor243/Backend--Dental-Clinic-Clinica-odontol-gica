package com.example.clinicaOdontologicaC47Sv7.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@ToString
@Getter
@Setter
@Entity
@Table(name="pacientes")
public class Paciente {

    @Id
    @SequenceGenerator(name="pacientes_sequence", sequenceName = "pacientes_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pacientes_sequencee")
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private int dni;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
    @JoinColumn(name="domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
    private Set<Turno> turnos = new HashSet<>();



public Paciente(){

}

    public Paciente(String nombre, String apellido, String email, int dni, Date fechaIngreso, Domicilio domicilio, Set<Turno> turnos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.turnos = turnos;
    }

    public Paciente(Long id, String nombre, String apellido, String email, int dni, Date fechaIngreso, Domicilio domicilio, Set<Turno> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.turnos = turnos;
    }
}
