package com.example.clinicaOdontologicaC47Sv7.repository.authLogin;

import com.example.clinicaOdontologicaC47Sv7.model.login.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface IUserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
