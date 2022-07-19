package com.example.clinicaOdontologicaC47Sv7.authLogin;

import com.example.clinicaOdontologicaC47Sv7.repository.authLogin.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service

public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Nombre de usuario no encontrado"));
    }
}
