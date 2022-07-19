package com.example.clinicaOdontologicaC47Sv7;

import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.dto.OdontologoDTO;
import com.example.clinicaOdontologicaC47Sv7.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class OdontologoServiceTest {

    private static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void guardarOdontologo(){
        logger.debug("Inicializando odontologo");
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("nombreOdontologo1");
        odontologoDTO.setApellido("apeliidoOdontologo1");
        odontologoDTO.setMatricula(12345678);

        logger.debug("Guardando odontologo");
        OdontologoDTO odontologoDTOGuardado = this.odontologoService.guardarOdontologo(odontologoDTO);

        Assertions.assertNotNull(odontologoDTOGuardado.getId());
    }

    @Test
    public void buscarOdontologo() throws Exception{
        logger.debug("Inicializando odontologo");
        OdontologoDTO odontologoDTO2 = new OdontologoDTO();
        odontologoDTO2.setNombre("nombreOdontologo1");
        odontologoDTO2.setApellido("apeliidoOdontologo1");
        odontologoDTO2.setMatricula(12345678);

        logger.debug("Guardando odontologo");
        OdontologoDTO odontologoDTOGuardado2 = this.odontologoService.guardarOdontologo(odontologoDTO2);
        OdontologoDTO odontologoDTODesdeDB = this.odontologoService.buscar(odontologoDTOGuardado2.getId());

        Assertions.assertNotNull(odontologoDTODesdeDB);
    }

    @Test
    public void actualizarOdontologo() throws Exception{
        logger.debug("Inicializando odontologo");
        OdontologoDTO odontologoDTO3 = new OdontologoDTO();
        odontologoDTO3.setNombre("nombreOdontologo3");
        odontologoDTO3.setApellido("apeliidoOdontologo3");
        odontologoDTO3.setMatricula(12345678);

        logger.debug("Guardando odontologo");
        OdontologoDTO odontologoDTOGuardado3 = this.odontologoService.guardarOdontologo(odontologoDTO3);
        odontologoDTOGuardado3.setNombre("nombreOdontologoActualizado3");
        this.odontologoService.actualizar(odontologoDTOGuardado3);

        OdontologoDTO odontologoDTODesdeDB = this.odontologoService.buscar(odontologoDTOGuardado3.getId());

        Assertions.assertEquals("nombreOdontologoActualizado3",odontologoDTODesdeDB.getNombre());
    }

    @Test
    public void borrarOdontologo() throws Exception{
        logger.debug("Inicializando odontologo");
        OdontologoDTO odontologoDTO4 = new OdontologoDTO();
        odontologoDTO4.setNombre("nombreOdontologo4");
        odontologoDTO4.setApellido("apeliidoOdontologo4");
        odontologoDTO4.setMatricula(12345678);

        logger.debug("Guardando odontologo");
        OdontologoDTO odontologoDTOGuardado4 = this.odontologoService.guardarOdontologo(odontologoDTO4);
        this.odontologoService.eliminar(odontologoDTOGuardado4.getId());


        Assertions.assertThrows(ResourceNotFoundException.class,() -> this.odontologoService.buscar(odontologoDTOGuardado4.getId()));
    }

    @Test
    public void traerTodosOdontologo() {
        logger.debug("Inicializando odontologo");
        OdontologoDTO odontologoDTO5 = new OdontologoDTO();
        odontologoDTO5.setNombre("nombreOdontologo5");
        odontologoDTO5.setApellido("apeliidoOdontologo5");
        odontologoDTO5.setMatricula(12345678);

        logger.debug("Guardando odontologo");
        OdontologoDTO odontologoDTOGuardado5 = this.odontologoService.guardarOdontologo(odontologoDTO5);

        Set<OdontologoDTO> odontologos = this.odontologoService.getTodos();


        Assertions.assertFalse(odontologos.isEmpty());
        Assertions.assertTrue(odontologos.size() > 0);
    }

}