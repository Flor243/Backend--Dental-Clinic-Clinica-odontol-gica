package com.example.clinicaOdontologicaC47Sv7.service;

import com.example.clinicaOdontologicaC47Sv7.exceptions.BadRequestException;
import com.example.clinicaOdontologicaC47Sv7.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaC47Sv7.model.Domicilio;
import com.example.clinicaOdontologicaC47Sv7.model.Odontologo;
import com.example.clinicaOdontologicaC47Sv7.model.Paciente;
import com.example.clinicaOdontologicaC47Sv7.model.Turno;
import com.example.clinicaOdontologicaC47Sv7.model.dto.DomicilioDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.OdontologoDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.PacienteDTO;
import com.example.clinicaOdontologicaC47Sv7.model.dto.TurnoDTO;
import com.example.clinicaOdontologicaC47Sv7.repository.IDomicilioRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.IOdontologoRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.IPacienteRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
public class TurnoService implements ITurnoService {



    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    private TurnoDTO cargarTurno(TurnoDTO turnoDTO){
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        Paciente paciente = mapper.convertValue(turnoDTO.getPacienteDTO(), Paciente.class);
        Odontologo odontologo = mapper.convertValue(turnoDTO.getOdontologoDTO(),Odontologo.class);
        Optional<Paciente> p = pacienteRepository.findById(paciente.getId());
        Optional<Odontologo> o = odontologoRepository.findById(odontologo.getId());
        turno.setPaciente(p.get());
        turno.setOdontologo(o.get());

        p.get().getTurnos().add(turno);
        o.get().getTurnos().add(turno);
        Turno turnoGuardado =turnoRepository.save(turno);
        TurnoDTO turnoDTOGuardado = mapper.convertValue(turnoGuardado, TurnoDTO.class);
        return turnoDTOGuardado;
    }

    @Override
    public TurnoDTO guardarTurno(TurnoDTO turnoDTO) throws BadRequestException {
        Long idPaciente = turnoDTO.getPacienteDTO().getId();

        Optional<Paciente> p = pacienteRepository.findById(idPaciente);

        Long idOdontologo = turnoDTO.getOdontologoDTO().getId();

        Optional<Odontologo> o = odontologoRepository.findById(idOdontologo);

        if (!p.isPresent() || !o.isPresent()){

            throw new BadRequestException("El paciente o el odóntologo no fue encontrado");
        }
        PacienteDTO pacienteDTO = mapper.convertValue(p.get(), PacienteDTO.class);
        turnoDTO.setPacienteDTO(pacienteDTO);
        OdontologoDTO odontologoDTO = mapper.convertValue(o.get(), OdontologoDTO.class);
        turnoDTO.setOdontologoDTO(odontologoDTO);
        TurnoDTO turnoDTOGuardado = cargarTurno(turnoDTO);
        return turnoDTOGuardado;
    }

    @Override
    public TurnoDTO buscar(Long id) throws ResourceNotFoundException{
        Optional<Turno> turno = turnoRepository.findById(id);
        TurnoDTO turnoDTO = null;
        if (turno.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el turno con el id: " + id);
        }
        if (turno.isPresent()) {
            turnoDTO= mapper.convertValue(turno, TurnoDTO.class);
        }
        Long idP =turno.get().getPaciente().getId();
        Long idO = turno.get().getOdontologo().getId();
        Optional<Paciente> p = pacienteRepository.findById(idP);
        Optional<Domicilio> domicilio = domicilioRepository.findById(idP);
        PacienteDTO pacienteDTO = mapper.convertValue(p, PacienteDTO.class);
        pacienteDTO.setDomicilioDTO(mapper.convertValue(domicilio, DomicilioDTO.class));
        turnoDTO.setPacienteDTO(pacienteDTO);
        Optional<Odontologo> o = odontologoRepository.findById(idO);
        turnoDTO.setOdontologoDTO(mapper.convertValue(o, OdontologoDTO.class));
        return turnoDTO;
    }

    @Override
    public void actualizar(TurnoDTO turnoDTO)  throws ResourceNotFoundException{

        Optional<Turno> t = turnoRepository.findById(turnoDTO.getIdTurno());
        if (t.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el turno con el id: " + turnoDTO.getIdTurno());
        }

        cargarTurno(turnoDTO);
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Turno> t = turnoRepository.findById(id);
        if (t.isEmpty()){
            throw new ResourceNotFoundException("No se ha encontrado el turno con el id: " + id);


        }
        turnoRepository.deleteById(id);

    }

    @Override
    public List<TurnoDTO> getTodos() {
        List<Turno> turnos =turnoRepository.findAll();
        List<TurnoDTO> turnoDTOS = new ArrayList<>();

        for (Turno turno:turnos) {
            Long idP =turno.getPaciente().getId();
            Long idO = turno.getOdontologo().getId();
            TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            Optional<Paciente> p = pacienteRepository.findById(idP);
            turnoDTO.setPacienteDTO(mapper.convertValue(p, PacienteDTO.class));
            Optional<Odontologo> o = odontologoRepository.findById(idO);
            turnoDTO.setOdontologoDTO(mapper.convertValue(o, OdontologoDTO.class));
            turnoDTOS.add(turnoDTO);
        }

        return turnoDTOS;
    }
}
