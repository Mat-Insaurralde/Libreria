
package com.LastByte.libreria.controladores;

import com.LastByte.libreria.entitis.Editorial;
import com.LastByte.libreria.exceptions.MiExcepcion;
import com.LastByte.libreria.service.EditorialService;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    
    
    @Autowired
    private EditorialService editorialService;
    
    
    @GetMapping("/registrar")
    public String registrar(){
    
    return  "editorial/editorial_form.html";
    }
    
    
   @PostMapping("/registro")
    public String registro(@RequestParam String nombre , ModelMap modelo) throws MiExcepcion {
        try {

            editorialService.crearEditorial(nombre);
            
            modelo.addAttribute("exito","Editorial guardado con exito!");
         
 
        } catch ( MiExcepcion e) {

            modelo.put("error", e.getMessage());
            
            
            return "editorial/editorial_form.html";
        }

      
  return "index.html";


    }
    
    
   @GetMapping("/lista") 
   public String lista (ModelMap modelo){
   List<Editorial> editoriales = editorialService.listaEditoriales();
       
   modelo.addAttribute("editoriales", editoriales);

   return "editorial/editoriales_lista.html";
   
   }
    
    
   
    @GetMapping("/modificar/{id}")
    public String modificar( @PathVariable String id,ModelMap modelo){
    
    modelo.put("editorial",  editorialService.getOne(id));
    
   return "editorial/editorial_modificar.html";
    }
    
    
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,String nombre,ModelMap modelo) 
    {
     
     
            try {
                
                
           editorialService.modificarEditorial(nombre, id);
           modelo.put("exito", "Nombre actualizado con exito!");
            return "redirect:../lista";
            
      
            } catch (MiExcepcion ex) {
            
             modelo.put("error", ex.getMessage());
            
             return "editorial/editorial_modificar.html";
            }
        
   
    }
            
            
    
    
}
