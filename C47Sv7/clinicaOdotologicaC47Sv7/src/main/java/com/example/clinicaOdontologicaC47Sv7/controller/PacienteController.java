package com.example.clinicaOdontologicaC47Sv7.controller;

import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.PacienteDTO;
import com.example.clinicaOdontologicaC47Sv7.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;



    @PostMapping
    public ResponseEntity<?> guardarPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacienteService.guardarPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtenerPaciente(@PathVariable Long id) throws ResourceNotFoundException{

        PacienteDTO pacienteDTO = pacienteService.buscar(id);



        return ResponseEntity.ok(pacienteDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ResourceNotFoundException {

            pacienteService.eliminar(id);

        return ResponseEntity.ok(HttpStatus.OK );
    }


    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {

            pacienteService.actualizar(pacienteDTO);

        return ResponseEntity.ok(HttpStatus.OK );

    }

    @GetMapping("/todos")
    public ResponseEntity<Collection<PacienteDTO>> obtenerLosPacientes(){
        return ResponseEntity.ok(pacienteService.getTodos());
    }

    @GetMapping("/porNombre/{name}")
    public ResponseEntity<PacienteDTO> obtenerPacientePorNombre(@PathVariable String name) throws ResourceNotFoundException{

        PacienteDTO pacienteDTO = pacienteService.buscarPorNombre(name);


        return ResponseEntity.ok(pacienteDTO);
    }
}



