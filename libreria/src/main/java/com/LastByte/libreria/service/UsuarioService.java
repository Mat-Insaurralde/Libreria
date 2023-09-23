package com.LastByte.libreria.service;

import com.LastByte.libreria.entitis.Usuario;
import com.LastByte.libreria.enumeraciones.Rol;
import com.LastByte.libreria.exceptions.MiExcepcion;
import com.LastByte.libreria.repository.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Matias Insaurralde
 */
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombre, String email, String password, String password2) throws MiExcepcion {

        validar(nombre, email, password, password2);

        Usuario usuario = new Usuario(null, nombre, email, new BCryptPasswordEncoder().encode(password), Rol.USER);

        usuarioRepositorio.save(usuario);

    }

    private void validar(String nombre, String email, String password, String password2) throws MiExcepcion {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiExcepcion("el nombre no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MiExcepcion("el email no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiExcepcion("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }

        if (!password.equals(password2)) {
            throw new MiExcepcion("Las contraseñas ingresadas deben ser iguales");
        }

    }

    
    
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {
    
            List<GrantedAuthority> permisos = new ArrayList<>();
                    
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession sesion = attr.getRequest().getSession(true);
            
            sesion.setAttribute("usuariosession", usuario);
                    
       return new User(usuario.getMail(), usuario.getPassword(),permisos);

        }else{return null;}

    }

}
