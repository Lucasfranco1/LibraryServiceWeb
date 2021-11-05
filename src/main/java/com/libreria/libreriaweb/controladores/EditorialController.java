/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.controladores;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.entidades.Editorial;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.servicios.AutorServicio;
import com.libreria.libreriaweb.servicios.EditorialServicio;
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
public class EditorialController {

    @Autowired
    private EditorialServicio editorialService;

    @GetMapping("/editoriales")
    public String iraEditorial() {
        return "editorial.html";
    }

    @GetMapping("editoriales/listar")//cualquiera de esas rutas
    public String listarEditoriales(ModelMap modelo) throws ErrorServicio {

        List<Editorial> todos = editorialService.listarAutores();
        modelo.addAttribute("editoriales", todos);
        return "editorial.html";

    }

    @GetMapping("/editoriales/nueva")
    public String MostrarFormOtraEditorial(Model modelo) {
        try {
            Editorial editorial = new Editorial();
            modelo.addAttribute("editoriales", editorial);
            return "form_editorial.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }

    }

    @PostMapping("/editoriales/registro")
    public String guardarEditorial(ModelMap modelo, @RequestParam String nombre) throws ErrorServicio {
        try {

            editorialService.guardarEditorial(nombre);

            modelo.put("titulo", "LibreryService");
            modelo.put("descrip", "Editorial cargada con éxito." );
            return "editorialexito.html";

        } catch (Exception e) {
            System.out.println(e.getMessage() + "Falló algo");
            return "redirect:/";

        }
    }

    @GetMapping("/editoriales/editar/{id}")
    public String mostrarFormEditarEditorial(@PathVariable String id, ModelMap modelo) throws ErrorServicio {

        modelo.addAttribute("editorial", editorialService.obtenerAutorPorId(id));
        return "editar_editorial.html";

    }

    @PostMapping("/editoriales/{id}")
    public String actualizarEditoriales(@PathVariable String id, @ModelAttribute("editorial") Editorial editorial, Model modelo) throws ErrorServicio {

        Editorial E = editorialService.obtenerAutorPorId(id);

        E.setId(id);
        E.setNombre(editorial.getNombre());

        editorialService.actualizarAutor(editorial);

        return "redirect:/editoriales";

    }

    @GetMapping("editoriales/baja/{id}")
    public String bajaEditorial(@PathVariable String id) {

        try {
            editorialService.baja(id);
            return "redirect:/editoriales/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
     @GetMapping("editoriales/alta/{id}")
    public String altaEditorial(@PathVariable String id) {

        try {
            editorialService.alta(id);
            return "redirect:/editoriales/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
    @GetMapping("editoriales/eliminar/{id}")
    public String eliminarEditorial(@PathVariable String id) {

        try {
            editorialService.eliminar(id);
            return "redirect:/editoriales/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
    

}
