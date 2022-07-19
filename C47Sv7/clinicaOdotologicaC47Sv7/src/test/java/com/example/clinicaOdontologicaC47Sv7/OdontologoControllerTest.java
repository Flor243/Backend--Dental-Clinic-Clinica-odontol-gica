package com.example.clinicaOdontologicaC47Sv7;

import com.example.clinicaOdontologicaC47Sv7.model.dto.OdontologoDTO;
import com.example.clinicaOdontologicaC47Sv7.service.OdontologoService;
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


@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void buscarOdontologoPorId() throws Exception{
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("nombreOdontologo1");
        odontologoDTO.setApellido("apellidoOdontologo1");
        odontologoDTO.setMatricula(12345678);

        OdontologoDTO odontologoDTOGuardado = odontologoService.guardarOdontologo(odontologoDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", ""+odontologoDTOGuardado.getId()+"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
        Assertions.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }
}
