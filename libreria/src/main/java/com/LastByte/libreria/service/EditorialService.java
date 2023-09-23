/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.LastByte.libreria.service;

import com.LastByte.libreria.entitis.Editorial;
import com.LastByte.libreria.exceptions.MiExcepcion;
import com.LastByte.libreria.repository.EditorialRepositorio;
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
public class EditorialService {

    
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    
    
    @Transactional
    public void crearEditorial(String nombre) throws MiExcepcion{
    
    validar(nombre);
        
    Editorial editorial = new Editorial(null, nombre);
    
    editorialRepositorio.save(editorial);
    
    }
    
    
    ///lista de editoriales
    public List<Editorial> listaEditoriales(){
    return new ArrayList<>(editorialRepositorio.findAll());
    }
    
    
    //modificar editorial
    
    public void modificarEditorial(String nombre,String id)throws MiExcepcion{
        
        validar(nombre);
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
    
        if (respuesta.isPresent()) {
        Editorial editorial = respuesta.get();
            
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
        }
    
    }
    
    
    
    
        private void validar(String nombre) throws MiExcepcion{
    
        if (nombre.isEmpty() || nombre==null) {
            
           throw new MiExcepcion("No ingreso un nombre!");
        }
   
    
    
    }
    
        public Editorial getOne(String id){
    
    return editorialRepositorio.findById(id).get();
    }
    
    
    
}
