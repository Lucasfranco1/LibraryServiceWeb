/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.controladores;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.entidades.Editorial;
import com.libreria.libreriaweb.entidades.Libro;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.servicios.AutorServicio;
import com.libreria.libreriaweb.servicios.EditorialServicio;

import com.libreria.libreriaweb.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lucas
 */
@Controller
@RequestMapping("/")
public class LibroController {

    @Autowired
    private LibroServicio lService;
    @Autowired
    private AutorServicio aService;
    @Autowired
    private EditorialServicio eService;

    @GetMapping("/libros")
    public String iraLibros() {
        return "libro.html";
    }

    @GetMapping("libros/listar")//cualquiera de esas rutas
    public String listarLibros(ModelMap modelo) throws ErrorServicio {

        List<Libro> todos = lService.listarLibros();
        modelo.addAttribute("libros", todos);
        return "libro.html";

    }


    @GetMapping("/libros/nuevo")
    public String MostrarFormOtroLibro(ModelMap modelo) {
        try {
            Libro libro = new Libro();
            modelo.addAttribute("libros", libro);
            modelo.addAttribute("autores", aService.listarAutores());
            modelo.addAttribute("editoriales", eService.listarEditoriales());
          return "form_libros.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }

    }
    

    @PostMapping("/libros/registro")
    public String guardarLibro(ModelMap modelo, @RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo, @RequestParam(required = false) String idAutor, @RequestParam(required = false) String idEditorial, @RequestParam(required = false) Integer anio, @RequestParam (required = false) Integer ejemplares,
            @RequestParam(required = false) Integer ejemplaresPrestados, @RequestParam(required = false) Integer ejemplaresRestantes) throws ErrorServicio {
        try {

            lService.crearLibro(isbn, titulo, idAutor, idEditorial, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);
            modelo.put("titulo", "LibreryService");
            modelo.put("descrip", "Tu libro fue registrado con éxito.");
            return "libroexito.html";

        } catch (Exception e) {
           
            modelo.put("error", e.getMessage());
            modelo.put("isbn", isbn);
            modelo.put("titulo", titulo);
            List<Autor>autores=aService.listarAutores();
            modelo.put("autores", autores);
            List<Editorial>editoriales=eService.listarEditoriales();
            modelo.put("editoriales", editoriales);
            modelo.put("año", anio);
            modelo.put("ejemplares", ejemplares);
            modelo.put("prestados", ejemplaresPrestados);
            modelo.put("restantes", ejemplaresRestantes);
            //muestro el error
                    
            System.out.println(e.getMessage() + "Falló algo");
            return "form_libros.html";

        }
    }

    @GetMapping("/libros/editar/{id}")
    public String mostrarFormEditar(@PathVariable String id, ModelMap modelo) throws ErrorServicio {

        modelo.addAttribute("libro", lService.obtenerLibroPorId(id));
        return "editar_libro.html";

    }

    @PostMapping("/libros/confirmar-edicion{id}")
    public String actualizarLibros(@PathVariable String id, ModelMap modelo,@RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo, @RequestParam(required = false) Integer anio, @RequestParam (required = false) Integer ejemplares,
            @RequestParam(required = false) Integer ejemplaresPrestados, @RequestParam(required = false) Integer ejemplaresRestantes ) throws ErrorServicio {

        try{
            lService.actualizarLibro(id,isbn,titulo,anio,ejemplares,ejemplaresPrestados,ejemplaresRestantes);
//            modelo.put("exito","Registro exitoso");
            return "redirect:/libros/listar";
        }catch(Exception e){
            modelo.put("error", "Faltó algún dato me parece...");
            return "form-libro";
        }
    }

    @GetMapping("libros/eliminar/{id}")
    public String eliminar(@PathVariable String id) {

        try {
            lService.eliminar(id);
            return "redirect:/libros/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
    @GetMapping("libros/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            lService.baja(id);
            return "redirect:/libros/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
     @GetMapping("libros/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            lService.alta(id);
            return "redirect:/libros/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

}
