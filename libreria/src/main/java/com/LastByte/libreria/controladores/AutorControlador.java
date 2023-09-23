
package com.LastByte.libreria.controladores;

import com.LastByte.libreria.entitis.Autor;
import com.LastByte.libreria.exceptions.MiExcepcion;
import com.LastByte.libreria.service.AutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Matias Insaurralde
 */
@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
   private AutorService autorService ;
    
    
    @GetMapping("/registrar")
    public String registrar(){
    
    return  "autor/autor_form.html";
    }
    
    
   @PostMapping("/registro")
    public String registro(@RequestParam String nombre,ModelMap modelo) throws MiExcepcion {
        try {

            autorService.crearAutor(nombre);
          
            modelo.put("exito", "Autor guardado con exito!");
            
        } catch ( MiExcepcion e) {

            
            modelo.put("error", e.getMessage());
            
            
            
            return "autor/autor_form.html";
        }

        return "index.html";



    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
    
        List<Autor> autores = autorService.listaAutores();
        
    modelo.addAttribute("autores",autores);
    
    
    return "autor/autor_lista.html";
    
    }
    
    
    
    @GetMapping("/modificar/{id}")
    public String modificar( @PathVariable String id,ModelMap modelo){
    
    modelo.put("autor",  autorService.getOne(id));
    
   return "autor/autor_modificar.html";
    }
    
    
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,String nombre,ModelMap modelo) 
    {
     
     
            try {
                
                
           autorService.modificarAutor(nombre, id);
           
           modelo.put("exito", "Autor actualizado con exito!");
           
           return "redirect:../lista";
            
      
            } catch (MiExcepcion ex) {
            
             modelo.put("error", ex.getMessage());
            
             return "autor/autor_modificar.html";
            }
        
   
    }
            
            
}
