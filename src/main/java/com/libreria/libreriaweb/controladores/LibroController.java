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

    @GetMapping("/libros")
    public String iraLibros() {
        return "libro.html";
    }

    @GetMapping("libros/listar")//cualquiera de esas rutas
    public String listarLibros(ModelMap modelo) throws ErrorServicio {

        List<Libro> todos = lService.listarLibros();
        modelo.addAttribute("libros", todos);
//        List<Libro> todo = lService.ListarTodo();
//        modelo.addAttribute("libros", todo);
        return "libro.html";

    }
    @GetMapping("libros/todos-autores{l.autor.nombre}")//cualquiera de esas rutas
    public String listarLibrosAutores(ModelMap modelo, @PathVariable String nombre) throws ErrorServicio {

        List<Libro> todos = lService.ListarTodo(nombre);
        modelo.addAttribute("libros", todos);
//        List<Libro> todo = lService.ListarTodo();
//        modelo.addAttribute("libros", todo);
        return "libro.html";

    }
//
//    @GetMapping("libros/todos")
//    public String listarTodo(ModelMap modelo) {
////      
//        List<Libro> todo = lService.ListarTodo();
//        modelo.addAttribute("libros", todo);
//        return "libro-todo.html";
////      
////        
//    }

    @GetMapping("/libros/nuevo")
    public String MostrarFormOtroLibro(ModelMap modelo) {
        try {
            Libro libro = new Libro();
            modelo.addAttribute("libros", libro);
            return "form_libros.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }

    }
    

    @PostMapping("libros/registro")
    public String guardarLibro(ModelMap modelo, @RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo, @RequestParam(required = false) String nombreAutor, @RequestParam(required = false) String nombreEditorial, @RequestParam(required = false) Integer anio, @RequestParam (required = false) Integer ejemplares,
            @RequestParam(required = false) Integer ejemplaresPrestados, @RequestParam(required = false) Integer ejemplaresRestantes) throws ErrorServicio {
        try {

            lService.crearLibro(isbn, titulo, nombreAutor, nombreEditorial, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);
            modelo.put("titulo", "LibreryService");
            modelo.put("descrip", "Tu libro fue registrado con éxito.");
            return "libroexito.html";

        } catch (Exception e) {
            System.out.println(e.getMessage() + "Falló algo");
            return "redirect:/";

        }
    }

    @GetMapping("/libros/editar/{id}")
    public String mostrarFormEditar(@PathVariable String id, ModelMap modelo) throws ErrorServicio {

        modelo.addAttribute("libro", lService.obtenerLibroPorId(id));
        return "editar_libro.html";

    }

    @PostMapping("/libros/{id}")
    public String actualizarLibros(@PathVariable String id, @ModelAttribute("libro") Libro libro, Model modelo) throws ErrorServicio {

        Libro E = lService.obtenerLibroPorId(id);

        E.setId(id);
        E.setIsbn(libro.getIsbn());
        E.setTitulo(libro.getTitulo());
        E.setAutor(libro.getAutor());
        E.setEditorial(libro.getEditorial());
        E.setAnio(libro.getAnio());
        E.setEjemplares(libro.getEjemplares());
        E.setEjemplaresPrestados(libro.getEjemplaresPrestados());
        E.setEjemplaresRestantes(libro.getEjemplaresRestantes());

        lService.actualizarLibro(libro);

        return "redirect:/libros";

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

    @GetMapping("libros/eliminar/{id}")
    public String eliminar(@PathVariable String id) {

        try {
            lService.eliminar(id);
            return "redirect:/libros/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

}
