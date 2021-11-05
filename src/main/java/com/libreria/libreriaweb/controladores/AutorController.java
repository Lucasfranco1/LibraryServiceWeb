/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.controladores;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AutorController {

    @Autowired
    private AutorServicio autorService;

    @GetMapping("/autores")
    public String iraAutor() {
        return "autor.html";
    }

    @GetMapping("autores/listar")//cualquiera de esas rutas
    public String listarAutores(ModelMap modelo) throws ErrorServicio {

        List<Autor> todos = autorService.listarAutores();
        modelo.addAttribute("autores", todos);
        return "autor.html";

    }

    @GetMapping("/autores/nuevo")
    public String MostrarFormOtroAutor(Model modelo) {
        try {
            Autor autor = new Autor();
            modelo.addAttribute("autores", autor);
            return "form_autor.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }

    }

    @PostMapping("/autores/registro")
    public String guardarAutor(ModelMap modelo, @RequestParam String nombre) throws ErrorServicio {
        try {

            autorService.guardarAutor(nombre);

            modelo.put("titulo", "LibreryService");
            modelo.put("descrip", "El autor fue registrado con éxito." );
            return "autorexito.html";

        } catch (Exception e) {
            System.out.println(e.getMessage() + "Falló algo");
            return "redirect:/";

        }
    }

    @GetMapping("/autores/editar/{id}")
    public String mostrarFormEditar(@PathVariable String id, ModelMap modelo) throws ErrorServicio {

        modelo.addAttribute("autor", autorService.obtenerAutorPorId(id));
        return "editar_autor.html";

    }

    @PostMapping("/autores/{id}")
    public String actualizarAutores(@PathVariable String id, @ModelAttribute("autor") Autor autor, Model modelo) throws ErrorServicio {

        Autor autorE = autorService.obtenerAutorPorId(id);

        autorE.setId(id);
        autorE.setNombre(autor.getNombre());

        autorService.actualizarAutor(autor);

        return "redirect:/autores";

    }

    @GetMapping("autores/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            autorService.baja(id);
            return "redirect:/autores/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
     @GetMapping("autores/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            autorService.alta(id);
            return "redirect:/autores/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
    @GetMapping("autores/eliminar/{id}")
    public String eliminar(@PathVariable String id) {

        try {
            autorService.eliminar(id);
            return "redirect:/autores/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
    

}
