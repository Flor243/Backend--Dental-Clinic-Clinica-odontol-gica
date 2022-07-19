package com.example.clinicaOdontologicaC47Sv7.controller;

import com.example.clinicaOdontologicaC47Sv7.exceptions.BadRequestException;
import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.TurnoDTO;
import com.example.clinicaOdontologicaC47Sv7.service.OdontologoService;
import com.example.clinicaOdontologicaC47Sv7.service.PacienteService;
import com.example.clinicaOdontologicaC47Sv7.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {



    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoDTO turnoDTO) throws BadRequestException {

            turnoService.guardarTurno(turnoDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> obtenerTurno(@PathVariable Long id) throws ResourceNotFoundException{

        TurnoDTO turnoDTO = turnoService.buscar(id);

        return ResponseEntity.ok(turnoDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ResourceNotFoundException {

            turnoService.eliminar(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {

            turnoService.actualizar(turnoDTO);

        return ResponseEntity.ok(HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.getTodos());
    }



}
