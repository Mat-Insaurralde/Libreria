
package com.LastByte.libreria.entitis;

import com.LastByte.libreria.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Matias Insaurralde
 */
@Entity
public class Usuario {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2" )
    private String id;
    
    
    
    private String nombre;
    private String mail;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String mail, String password, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
    
    
    
    
    
    
}
