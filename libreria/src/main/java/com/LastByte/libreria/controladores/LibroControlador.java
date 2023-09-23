package com.LastByte.libreria.controladores;

import com.LastByte.libreria.entitis.Autor;
import com.LastByte.libreria.entitis.Editorial;
import com.LastByte.libreria.entitis.Libro;
import com.LastByte.libreria.exceptions.MiExcepcion;
import com.LastByte.libreria.service.AutorService;
import com.LastByte.libreria.service.EditorialService;
import com.LastByte.libreria.service.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Matias Insaurralde
 */

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Autor> autores = autorService.listaAutores();
        List<Editorial> editoriales = editorialService.listaEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro/libro_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo, @RequestParam(required = false) Integer ejemplares,
             @RequestParam(required = false) String idAutor, @RequestParam(required = false) String idEditorial, ModelMap modelo) {
        try {

            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

            modelo.put("exito", "Libro guardado con exito!");

        } catch (MiExcepcion e) {

            modelo.put("error", e.getMessage());

            List<Autor> autores = autorService.listaAutores();
            List<Editorial> editoriales = editorialService.listaEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            return "libro/libro_form.html";
        }

        return "libro/index.html";
    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Libro> libros = libroService.listaLibros();
        modelo.addAttribute("libros", libros);

        return "libro/libro_lista.html";
    }

    @GetMapping("modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {

        modelo.put("libro", libroService.getOne(isbn));

        List<Autor> autores = autorService.listaAutores();
        List<Editorial> editoriales = editorialService.listaEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro/libro_modificar";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, String idautor, String ideditorial, Integer ejemplares, ModelMap modelo) {

        try {

            libroService.modificarLibro(isbn, titulo, idautor, ideditorial, ejemplares);

            modelo.put("exito", "Libro actualizado con exito!");

            return "redirect:../lista";

        } catch (MiExcepcion ex) {

            modelo.put("error", ex.getMessage());

            List<Autor> autores = autorService.listaAutores();
            List<Editorial> editoriales = editorialService.listaEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            return "libro/libro_modificar.html";
        }

    }

}
