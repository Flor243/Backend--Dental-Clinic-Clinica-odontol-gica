package com.example.clinicaOdontologicaC47Sv7;

import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.DomicilioDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.PacienteDTO;
import com.example.clinicaOdontologicaC47Sv7.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Set;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    public void guardarPaciente(){
        DomicilioDTO domicilioDTO = new DomicilioDTO();
        domicilioDTO.setCalle("calle");
        domicilioDTO.setNumero("1234");
        domicilioDTO.setLocalidad("l");
        domicilioDTO.setProvincia("p");

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre("n");
        pacienteDTO.setApellido("a");
        pacienteDTO.setDni(12345678);
        pacienteDTO.setEmail("n@mail.com");
        pacienteDTO.setFechaIngreso(new Date());
        pacienteDTO.setDomicilioDTO(domicilioDTO);

        PacienteDTO pacienteDTOGuardado = pacienteService.guardarPaciente(pacienteDTO);

        Assertions.assertNotNull(pacienteDTOGuardado.getId());
    }

    @Test
    public void buscarPaciente() throws Exception{
        DomicilioDTO domicilioDTO2 = new DomicilioDTO();
        domicilioDTO2.setCalle("calle2");
        domicilioDTO2.setNumero("1234");
        domicilioDTO2.setLocalidad("l2");
        domicilioDTO2.setProvincia("p2");

        PacienteDTO pacienteDTO2 = new PacienteDTO();
        pacienteDTO2.setNombre("n2");
        pacienteDTO2.setApellido("a2");
        pacienteDTO2.setDni(12345678);
        pacienteDTO2.setEmail("n2@mail.com");
        pacienteDTO2.setFechaIngreso(new Date());
        pacienteDTO2.setDomicilioDTO(domicilioDTO2);

        PacienteDTO pacienteDTOGuardado2 = pacienteService.guardarPaciente(pacienteDTO2);

        PacienteDTO pacienteDTODesdeDB = pacienteService.buscar(pacienteDTOGuardado2.getId());

        Assertions.assertNotNull(pacienteDTODesdeDB);


    }

    @Test
    public void actualizarPaciente() throws Exception{
        DomicilioDTO domicilioDTO3 = new DomicilioDTO();
        domicilioDTO3.setCalle("calle3");
        domicilioDTO3.setNumero("1234");
        domicilioDTO3.setLocalidad("l3");
        domicilioDTO3.setProvincia("p3");

        PacienteDTO pacienteDTO3 = new PacienteDTO();
        pacienteDTO3.setNombre("n3");
        pacienteDTO3.setApellido("a3");
        pacienteDTO3.setDni(12345678);
        pacienteDTO3.setEmail("n3@mail.com");
        pacienteDTO3.setFechaIngreso(new Date());
        pacienteDTO3.setDomicilioDTO(domicilioDTO3);

        PacienteDTO pacienteDTOGuardado3= pacienteService.guardarPaciente(pacienteDTO3);
        pacienteDTOGuardado3.setNombre("n3Actualizado");
        pacienteDTOGuardado3.setDomicilioDTO(domicilioDTO3);
        pacienteService.actualizar(pacienteDTOGuardado3);

        PacienteDTO pacienteDTODesdeDB = pacienteService.buscar(pacienteDTOGuardado3.getId());

        Assertions.assertEquals("n3Actualizado", pacienteDTODesdeDB.getNombre());


    }

    @Test
    public void borrarPaciente() throws Exception{
        DomicilioDTO domicilioDTO4 = new DomicilioDTO();
        domicilioDTO4.setCalle("calle4");
        domicilioDTO4.setNumero("1234");
        domicilioDTO4.setLocalidad("l4");
        domicilioDTO4.setProvincia("p4");

        PacienteDTO pacienteDTO4 = new PacienteDTO();
        pacienteDTO4.setNombre("n4");
        pacienteDTO4.setApellido("a4");
        pacienteDTO4.setDni(12345678);
        pacienteDTO4.setEmail("n4@mail.com");
        pacienteDTO4.setFechaIngreso(new Date());
        pacienteDTO4.setDomicilioDTO(domicilioDTO4);

        PacienteDTO pacienteDTOGuardado4= pacienteService.guardarPaciente(pacienteDTO4);
        pacienteService.eliminar(pacienteDTOGuardado4.getId());



        Assertions.assertThrows(ResourceNotFoundException.class, ()->pacienteService.buscar(pacienteDTOGuardado4.getId()));


    }

    @Test
    public void traerTodosPaciente() {
        DomicilioDTO domicilioDTO5 = new DomicilioDTO();
        domicilioDTO5.setCalle("calle5");
        domicilioDTO5.setNumero("1234");
        domicilioDTO5.setLocalidad("l5");
        domicilioDTO5.setProvincia("p5");

        PacienteDTO pacienteDTO4 = new PacienteDTO();
        pacienteDTO4.setNombre("n5");
        pacienteDTO4.setApellido("a5");
        pacienteDTO4.setDni(12345678);
        pacienteDTO4.setEmail("n5@mail.com");
        pacienteDTO4.setFechaIngreso(new Date());
        pacienteDTO4.setDomicilioDTO(domicilioDTO5);

        PacienteDTO pacienteDTOGuardado5= pacienteService.guardarPaciente(pacienteDTO4);

        Set<PacienteDTO> pacientes = pacienteService.getTodos();

        Assertions.assertFalse(pacientes.isEmpty());
        Assertions.assertTrue(pacientes.size() > 0);


    }





}
