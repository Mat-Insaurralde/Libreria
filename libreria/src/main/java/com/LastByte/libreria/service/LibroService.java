package com.LastByte.libreria.service;

import com.LastByte.libreria.entitis.Autor;
import com.LastByte.libreria.entitis.Editorial;
import com.LastByte.libreria.entitis.Libro;
import com.LastByte.libreria.exceptions.MiExcepcion;
import com.LastByte.libreria.repository.AutorRepositorio;
import com.LastByte.libreria.repository.EditorialRepositorio;
import com.LastByte.libreria.repository.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
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
public class LibroService {

    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Autowired
    private LibroRepositorio libroRepositorio;

    
    
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro(isbn, titulo, ejemplares, new Date(), autor, editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> listaLibros() {
        return new ArrayList<>(libroRepositorio.findAll());
    }

    
    
    public void modificarLibro(Long isbn, String titulo, String idautor, String ideditorial,Integer ejemplares) throws MiExcepcion {
        
        validar(isbn, titulo, ejemplares, idautor, ideditorial);
        
        Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idautor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(ideditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        
        
        
        
        
        if (respuestaAutor.isPresent()) {
            
            autor=respuestaAutor.get();
        }
        
         if (respuestaEditorial.isPresent()) {
            
            editorial=respuestaEditorial.get();
        }
        
         if (respuestaLibro.isPresent()) {
       
          Libro libro = respuestaLibro.get();
             
          libro.setTitulo(titulo);
           libro.setAutor(autor);
           libro.setEditorial(editorial);
           libro.setEjemplares(ejemplares);
           
           libroRepositorio.save(libro);
        }
         
         
        
        
        
        
        
    }

    
    
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion{
       
        if(isbn == null){
            throw new MiExcepcion("el isbn no puede ser nulo"); //
        }
        if(titulo.isEmpty() || titulo == null){
            throw new MiExcepcion("el titulo no puede ser nulo o estar vacio");
        }
        if(ejemplares == null){
            throw new MiExcepcion("ejemplares no puede ser nulo");
        }
        if(idAutor.isEmpty() || idAutor == null){
            throw new MiExcepcion("el Autor no puede ser nulo o estar vacio");
        }
        
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MiExcepcion("La Editorial no puede ser nula o estar vacia");
        }
    }


public Libro getOne(Long isbn){

return libroRepositorio.getOne(isbn);
}





}

