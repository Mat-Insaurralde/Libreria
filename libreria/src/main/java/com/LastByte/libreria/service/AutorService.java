
package com.LastByte.libreria.service;

import com.LastByte.libreria.entitis.Autor;
import com.LastByte.libreria.exceptions.MiExcepcion;
import com.LastByte.libreria.repository.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matias Insaurralde
 */

@Service
public class AutorService {
    
    
@Autowired
    private AutorRepositorio autorRepositorio;
    
    
    @Transactional
    public void crearAutor(String nombre) throws MiExcepcion{
    
    validar(nombre);
        
    Autor autor = new Autor(null,nombre);
    
    autorRepositorio.save(autor);
    }
    
    
    
    /// Lista de autores
    public List<Autor> listaAutores(){
    return  new ArrayList<> (autorRepositorio.findAll()) ;
    }
    
    
    
    //modificar autor
      public void modificarAutor(String nombre,String id) throws MiExcepcion{
    
          validar(nombre);
              
       Optional<Autor> respuesta = autorRepositorio.findById(id);
    
        if (respuesta.isPresent()) {
        Autor autor = respuesta.get();
            
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
        }
       
    
    }
    
      
      
    private void validar(String nombre) throws MiExcepcion{
    
        if (nombre.isEmpty() || nombre==null) {
            
           throw new MiExcepcion("No ingreso un nombre!");
        }
   
    
    
    }
    

    public Autor getOne(String id){
    
    return autorRepositorio.findById(id).get();
    }
    
}
