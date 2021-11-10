/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.controladores;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.entidades.Cliente;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.servicios.AutorServicio;
import com.libreria.libreriaweb.servicios.ClienteServicio;
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

@Controller
@RequestMapping("/")
public class ClienteControlador {
    @Autowired
    private ClienteServicio cService;

    @GetMapping("/clientes")
    public String iraClientes() {
        return "cliente.html";
    }

    @GetMapping("clientes/listar")//cualquiera de esas rutas
    public String listarClientes(ModelMap modelo) throws ErrorServicio {

        List<Cliente> todos = cService.listarClientes();
        modelo.addAttribute("clientes", todos);
        return "cliente.html";

    }

    @GetMapping("/clientes/nuevo")
    public String MostrarFormOtroCliente(Model modelo) {
        try {
            Cliente c = new Cliente();
            modelo.addAttribute("clientes", c);
            return "form_cliente.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }

    }

    @PostMapping("/clientes/registro")
    public String guardarCliente(ModelMap modelo, @RequestParam(required = false) Long documento,@RequestParam(required = false) String nombre,
    @RequestParam(required = false) String apellido, @RequestParam(required = false) String telefono) throws ErrorServicio {
        try {
           
            cService.crearCliente(documento, nombre, apellido, telefono);

            modelo.put("titulo", "LibreryService");
            modelo.put("descrip", "El cliente fue registrado con éxito." );
            return "clienteexito.html";

        } catch (Exception e) {
            modelo.put("error", e.getMessage()); 
            modelo.put("documento", documento);
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("telefono", telefono);
            System.out.println("Error por "+e.getMessage());
            return "form_cliente.html";

        }
    }

    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormEditarCliente(@PathVariable String id, ModelMap modelo) throws ErrorServicio {

        modelo.addAttribute("cliente", cService.buscarClienteId(id));
       
        return "editar_cliente.html";

    }

    @PostMapping("/clientes/{id}")
    public String actualizarAutores(@PathVariable String id, @ModelAttribute("cliente") Cliente cliente, ModelMap modelo) throws ErrorServicio {

        Cliente cE = cService.buscarClienteId(id);

        cE.setId(id);
        cE.setNombre(cliente.getNombre());
        cE.setNombre(cliente.getApellido());
        cE.setNombre(cliente.getTelefono());
        cService.modificarCliente(cliente);
        modelo.put("titulo", "¡Modificación Exitosa!");
        modelo.put("descrip", "El cliente fue modificado con éxito." );
        return "clienteexito.html";

    }

    @GetMapping("clientes/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            cService.baja(id);
            return "redirect:/clientes/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
     @GetMapping("clientes/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            cService.alta(id);
            return "redirect:/clientes/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
//    @GetMapping("clientes/eliminar/{id}")
//    public String eliminar(@PathVariable String id) {
//
//        try {
//            cService.eliminar(id);
//            return "redirect:/clientes/listar";
//        } catch (Exception e) {
//            return "redirect:/";
//        }
//
//    }
//    
}
