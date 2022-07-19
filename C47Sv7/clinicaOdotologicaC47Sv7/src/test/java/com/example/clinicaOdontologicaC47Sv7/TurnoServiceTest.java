package com.example.clinicaOdontologicaC47Sv7;

import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.DomicilioDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.OdontologoDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.PacienteDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.TurnoDTO;
import com.example.clinicaOdontologicaC47Sv7.service.OdontologoService;
import com.example.clinicaOdontologicaC47Sv7.service.PacienteService;
import com.example.clinicaOdontologicaC47Sv7.service.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void guardarTurno() throws Exception{
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

        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("nombreOdontologo1");
        odontologoDTO.setApellido("apellidoOdontologo1");
        odontologoDTO.setMatricula(12345678);
        OdontologoDTO odontologoDTOGuardado = odontologoService.guardarOdontologo(odontologoDTO);


        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFecha(new Date());
        turnoDTO.setHora("9:00");
        turnoDTO.setPacienteDTO(pacienteDTOGuardado);
        turnoDTO.setOdontologoDTO(odontologoDTOGuardado);

        TurnoDTO turnoDTOGuardado = turnoService.guardarTurno(turnoDTO);
        Assertions.assertNotNull(turnoDTOGuardado);
    }

    @Test
    public void buscarTurno() throws Exception{
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

        OdontologoDTO odontologoDTO2 = new OdontologoDTO();
        odontologoDTO2.setNombre("nombreOdontologo1");
        odontologoDTO2.setApellido("apellidoOdontologo1");
        odontologoDTO2.setMatricula(12345678);
        OdontologoDTO odontologoDTOGuardado2 = odontologoService.guardarOdontologo(odontologoDTO2);


        TurnoDTO turnoDTO2 = new TurnoDTO();
        turnoDTO2.setFecha(new Date());
        turnoDTO2.setHora("9:00");
        turnoDTO2.setPacienteDTO(pacienteDTOGuardado2);
        turnoDTO2.setOdontologoDTO(odontologoDTOGuardado2);

        TurnoDTO turnoDTOGuardado2 = turnoService.guardarTurno(turnoDTO2);
        TurnoDTO turnoDTODesdeDB = turnoService.buscar(turnoDTOGuardado2.getIdTurno());
        Assertions.assertNotNull(turnoDTODesdeDB);
    }

    @Test
    public void actualizarTurno() throws Exception{
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
        PacienteDTO pacienteDTOGuardado3 = pacienteService.guardarPaciente(pacienteDTO3);

        OdontologoDTO odontologoDTO3 = new OdontologoDTO();
        odontologoDTO3.setNombre("nombreOdontologo3");
        odontologoDTO3.setApellido("apellidoOdontologo3");
        odontologoDTO3.setMatricula(12345678);
        OdontologoDTO odontologoDTOGuardado3 = odontologoService.guardarOdontologo(odontologoDTO3);


        TurnoDTO turnoDTO3 = new TurnoDTO();
        turnoDTO3.setFecha(new Date());
        turnoDTO3.setHora("9:30");
        turnoDTO3.setPacienteDTO(pacienteDTOGuardado3);
        turnoDTO3.setOdontologoDTO(odontologoDTOGuardado3);

        TurnoDTO turnoDTOGuardado3 = turnoService.guardarTurno(turnoDTO3);
        turnoDTOGuardado3.setHora("10:00");
        turnoDTOGuardado3.setPacienteDTO(pacienteDTOGuardado3);
        turnoDTOGuardado3.setOdontologoDTO(odontologoDTOGuardado3);
        turnoService.actualizar(turnoDTOGuardado3);

        TurnoDTO turnoDTODesdeDB = turnoService.buscar(turnoDTOGuardado3.getIdTurno());

        Assertions.assertEquals("10:00", turnoDTODesdeDB.getHora());
    }

    @Test
    public void borrarTurno() throws Exception{
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
        PacienteDTO pacienteDTOGuardado4 = pacienteService.guardarPaciente(pacienteDTO4);

        OdontologoDTO odontologoDTO4 = new OdontologoDTO();
        odontologoDTO4.setNombre("nombreOdontologo4");
        odontologoDTO4.setApellido("apellidoOdontologo4");
        odontologoDTO4.setMatricula(12345678);
        OdontologoDTO odontologoDTOGuardado4 = odontologoService.guardarOdontologo(odontologoDTO4);


        TurnoDTO turnoDTO4 = new TurnoDTO();
        turnoDTO4.setFecha(new Date());
        turnoDTO4.setHora("9:00");
        turnoDTO4.setPacienteDTO(pacienteDTOGuardado4);
        turnoDTO4.setOdontologoDTO(odontologoDTOGuardado4);

        TurnoDTO turnoDTOGuardado4 = turnoService.guardarTurno(turnoDTO4);

        turnoService.eliminar(turnoDTOGuardado4.getIdTurno());

        Assertions.assertThrows(ResourceNotFoundException.class, ()-> turnoService.buscar(turnoDTOGuardado4.getIdTurno()));
    }


    @Test
    public void traerTodosTurno() throws Exception{
        DomicilioDTO domicilioDTO5 = new DomicilioDTO();
        domicilioDTO5.setCalle("calle5");
        domicilioDTO5.setNumero("1234");
        domicilioDTO5.setLocalidad("l5");
        domicilioDTO5.setProvincia("p5");

        PacienteDTO pacienteDTO5 = new PacienteDTO();
        pacienteDTO5.setNombre("n5");
        pacienteDTO5.setApellido("a5");
        pacienteDTO5.setDni(12345678);
        pacienteDTO5.setEmail("n5@mail.com");
        pacienteDTO5.setFechaIngreso(new Date());
        pacienteDTO5.setDomicilioDTO(domicilioDTO5);
        PacienteDTO pacienteDTOGuardado5 = pacienteService.guardarPaciente(pacienteDTO5);

        OdontologoDTO odontologoDTO5 = new OdontologoDTO();
        odontologoDTO5.setNombre("nombreOdontologo5");
        odontologoDTO5.setApellido("apellidoOdontologo5");
        odontologoDTO5.setMatricula(12345678);
        OdontologoDTO odontologoDTOGuardado5 = odontologoService.guardarOdontologo(odontologoDTO5);


        TurnoDTO turnoDTO5 = new TurnoDTO();
        turnoDTO5.setFecha(new Date());
        turnoDTO5.setHora("9:00");
        turnoDTO5.setPacienteDTO(pacienteDTOGuardado5);
        turnoDTO5.setOdontologoDTO(odontologoDTOGuardado5);

        TurnoDTO turnoDTOGuardado5 = turnoService.guardarTurno(turnoDTO5);

        List<TurnoDTO> turnos = turnoService.getTodos();

        Assertions.assertFalse(turnos.isEmpty());
        Assertions.assertTrue(turnos.size() > 0);
    }

}
