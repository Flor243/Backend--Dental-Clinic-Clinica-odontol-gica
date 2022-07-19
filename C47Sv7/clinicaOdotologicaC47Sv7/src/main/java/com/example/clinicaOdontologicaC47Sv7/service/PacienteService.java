package com.example.clinicaOdontologicaC47Sv7.service;


import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.Domicilio;
import com.example.clinicaOdontologicaC47Sv7.model.Paciente;
import com.example.clinicaOdontologicaC47Sv7.model.dto.DomicilioDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.PacienteDTO;
import com.example.clinicaOdontologicaC47Sv7.repository.IDomicilioRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.IPacienteRepository;
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
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IDomicilioRepository domicilioRepository;
    @Autowired
    ObjectMapper mapper;


    private PacienteDTO cargarPaciente(PacienteDTO pacienteDTO){
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);

        Domicilio domicilio = mapper.convertValue(pacienteDTO.getDomicilioDTO(), Domicilio.class);
        Paciente p= pacienteRepository.save(paciente);

        Optional<Paciente> pdesdeDB= pacienteRepository.findById(p.getId());

        //domicilioRepository.save(domicilio);
        domicilio.setPaciente(pdesdeDB.get());

        pdesdeDB.get().setDomicilio(domicilio);
        Paciente pacienteGuardado = pacienteRepository.save(pdesdeDB.get());
        PacienteDTO pacienteDTOGuardado = mapper.convertValue(pacienteGuardado, PacienteDTO.class);

        return pacienteDTOGuardado;



    }

    @Override
    public PacienteDTO guardarPaciente(PacienteDTO pacienteDTO) {
        PacienteDTO pacienteDTOGuardado = cargarPaciente(pacienteDTO);
        return pacienteDTOGuardado;
    }

    @Override
    public PacienteDTO buscar(Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        if (paciente.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el paciente con el id: " + id);
        }
        if (paciente.isPresent()) {
            pacienteDTO= mapper.convertValue(paciente, PacienteDTO.class);
        }
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        assert pacienteDTO != null;
        pacienteDTO.setDomicilioDTO(mapper.convertValue(domicilio, DomicilioDTO.class));
        return pacienteDTO;
    }

    @Override
    public void actualizar(PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        Optional<Paciente> p = pacienteRepository.findById(pacienteDTO.getId());
        if (p.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el paciente con el id: " + pacienteDTO.getId());
        }
        cargarPaciente(pacienteDTO);
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Paciente> p = pacienteRepository.findById(id);
        if (p.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el paciente con el id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public Set<PacienteDTO> getTodos() {
        List<Paciente> pacientes =pacienteRepository.findAll();
        Set<PacienteDTO> pacienteDTOS = new HashSet<>();

        for (Paciente P:pacientes) {
            pacienteDTOS.add(mapper.convertValue(P, PacienteDTO.class));
        }

        return pacienteDTOS;
    }

    @Override
    public PacienteDTO buscarPorNombre(String name) throws ResourceNotFoundException{
        Optional<Paciente> paciente = pacienteRepository.findPacienteByName(name);
        PacienteDTO pacienteDTO = null;
        if (paciente.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el paciente con el nombre: " + name);
        }
        if (paciente.isPresent()) {
            pacienteDTO= mapper.convertValue(paciente, PacienteDTO.class);
        }
        Optional<Domicilio> domicilio = domicilioRepository.findById(paciente.get().getId());
        assert pacienteDTO != null;
        pacienteDTO.setDomicilioDTO(mapper.convertValue(domicilio, DomicilioDTO.class));
        return pacienteDTO;
    }
}