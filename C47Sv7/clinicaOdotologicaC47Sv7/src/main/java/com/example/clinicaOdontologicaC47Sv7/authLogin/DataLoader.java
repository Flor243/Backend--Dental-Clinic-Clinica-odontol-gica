package com.example.clinicaOdontologicaC47Sv7.authLogin;


import com.example.clinicaOdontologicaC47Sv7.model.Odontologo;
import com.example.clinicaOdontologicaC47Sv7.model.Paciente;
import com.example.clinicaOdontologicaC47Sv7.model.login.Usuario;
import com.example.clinicaOdontologicaC47Sv7.model.login.UsuarioRole;
import com.example.clinicaOdontologicaC47Sv7.repository.IDomicilioRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.IOdontologoRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.IPacienteRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.ITurnoRepository;
import com.example.clinicaOdontologicaC47Sv7.repository.authLogin.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private IDomicilioRepository domicilioRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passworsAdmin = passwordEncoder.encode("admin");
        String passwordUser = passwordEncoder.encode("user");

        userRepository.save( new Usuario("nombreAdmin", "adminUser", "admin@mail.com",passworsAdmin, UsuarioRole.ADMIN));
        userRepository.save(new Usuario("nombreUser","userUser", "user@mail.com",passwordUser, UsuarioRole.USER));

        Odontologo o1 = new Odontologo();
        o1.setNombre("o1");
        o1.setApellido("a1");
        o1.setMatricula(1234);
        odontologoRepository.save(o1);

        Odontologo o2 = new Odontologo();
        o2.setNombre("o2");
        o2.setApellido("a2");
        o2.setMatricula(1234);
        odontologoRepository.save(o2);



    }
}
