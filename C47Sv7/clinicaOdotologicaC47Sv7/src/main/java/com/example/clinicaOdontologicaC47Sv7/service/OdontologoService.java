package com.example.clinicaOdontologicaC47Sv7.service;


import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.Odontologo;
import com.example.clinicaOdontologicaC47Sv7.model.dto.OdontologoDTO;
import com.example.clinicaOdontologicaC47Sv7.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class OdontologoService implements IOdontologoService {

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    private OdontologoDTO cargarOdontologo(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        OdontologoDTO odontologoDTOGuardado = mapper.convertValue(odontologoGuardado, OdontologoDTO.class);
        return odontologoDTOGuardado;
    }

    @Override
    public OdontologoDTO guardarOdontologo(OdontologoDTO odontologoDTO) {

        OdontologoDTO odontologoDTOGuardado = cargarOdontologo(odontologoDTO);
        return odontologoDTOGuardado;
    }

    @Override
    public OdontologoDTO buscar(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        if (odontologo.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el odontólogo con el id: " + id);
        }
        if (odontologo.isPresent()) {
             odontologoDTO= mapper.convertValue(odontologo, OdontologoDTO.class);
        }

        return odontologoDTO;
    }

    @Override
    public void actualizar(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        Optional<Odontologo> o = odontologoRepository.findById(odontologoDTO.getId());
        if (o.isEmpty()){
            throw new ResourceNotFoundException("No se pudo encontrar el odontólogo con el id: " + odontologoDTO.getId());
        }
        cargarOdontologo(odontologoDTO);
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> o = odontologoRepository.findById(id);
        if (o.isEmpty()){
            throw new ResourceNotFoundException("No se pudo encontrar el odontólogo con el id: " + id);
        }
        odontologoRepository.deleteById(id);
    }

    @Override
    public Set<OdontologoDTO> getTodos() {

        List<Odontologo> odontologos =odontologoRepository.findAll();
        Set<OdontologoDTO> odontologoDTOS = new HashSet<>();

        for (Odontologo o:odontologos) {
            odontologoDTOS.add(mapper.convertValue(o, OdontologoDTO.class));
        }

        return odontologoDTOS;
    }

    @Override
    public OdontologoDTO buscarPorNombre(String name) throws ResourceNotFoundException{
        Optional<Odontologo> odontologo = odontologoRepository.findOdontologoByName(name);
        OdontologoDTO odontologoDTO = null;
        if (odontologo.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el odontólogo con el nombre: " + name);
        }
        if (odontologo.isPresent()) {
            odontologoDTO= mapper.convertValue(odontologo, OdontologoDTO.class);
        }

        return odontologoDTO;


    }
}
