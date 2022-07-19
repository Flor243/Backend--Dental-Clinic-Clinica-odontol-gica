package com.example.clinicaOdontologicaC47Sv7.model.login;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails {

    @Id
    @SequenceGenerator(name="usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    private Long id;
    private String nombre;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UsuarioRole usuarioRoles;


    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioRoles.name());

        return Collections.singleton(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Usuario(String nombre, String username, String email, String password, UsuarioRole usuarioRoles) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.usuarioRoles = usuarioRoles;
    }

    public Usuario(Long id, String nombre, String username, String email, String password, UsuarioRole usuarioRoles) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.usuarioRoles = usuarioRoles;
    }
}
