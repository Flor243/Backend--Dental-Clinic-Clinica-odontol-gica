package com.example.clinicaOdontologicaC47Sv7.controller;


import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.OdontologoDTO;
import com.example.clinicaOdontologicaC47Sv7.service.IOdontologoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private IOdontologoService odontologoService;



    @PostMapping
    public ResponseEntity<?> guardarOdontologo(@RequestBody OdontologoDTO odontologoDTO){
        odontologoService.guardarOdontologo(odontologoDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> obtenerOdontologo(@PathVariable Long id) throws ResourceNotFoundException{

        OdontologoDTO odontologoDTO = odontologoService.buscar(id);


        return ResponseEntity.ok(odontologoDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ResourceNotFoundException {

            odontologoService.eliminar(id);

        return ResponseEntity.ok(HttpStatus.OK );
    }


    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {

            odontologoService.actualizar(odontologoDTO);

        return ResponseEntity.ok(HttpStatus.OK );

    }

    @GetMapping("/todos")
    public ResponseEntity<Collection<OdontologoDTO>> obtenerLosOdontologos(){
        return ResponseEntity.ok(odontologoService.getTodos());
    }

    @GetMapping("/porNombre/{name}")
    public ResponseEntity<OdontologoDTO> obtenerOdontologoPorNombre(@PathVariable String name) throws ResourceNotFoundException{

        OdontologoDTO odontologoDTO = odontologoService.buscarPorNombre(name);


        return ResponseEntity.ok(odontologoDTO);
    }

}
