
package com.LastByte.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Matias Insaurralde
 */

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @GetMapping("/dashboard")
    public String panelAdministrativo(){
    
    return "panel.html";
    }
    
    
    
    
    
    
    
    
    
}
