package com.example.clinicaOdontologicaC47Sv7;

import com.example.clinicaOdontologicaC47Sv7.model.dto.DomicilioDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.PacienteDTO;
import com.example.clinicaOdontologicaC47Sv7.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteService pacienteService;

    @Test
    public void buccarPacientePorId() throws Exception{
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

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", ""+pacienteDTOGuardado.getId()+"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
        Assertions.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }
}
