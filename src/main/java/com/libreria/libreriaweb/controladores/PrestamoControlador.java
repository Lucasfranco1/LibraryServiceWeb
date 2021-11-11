/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.controladores;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.entidades.Cliente;
import com.libreria.libreriaweb.entidades.Editorial;
import com.libreria.libreriaweb.entidades.Libro;
import com.libreria.libreriaweb.entidades.Prestamo;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.servicios.ClienteServicio;
import com.libreria.libreriaweb.servicios.LibroServicio;
import com.libreria.libreriaweb.servicios.PrestamoServicio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio pS;
    @Autowired
    private LibroServicio lS;
    @Autowired
    private ClienteServicio cS;

    @GetMapping("/prestamos")
    public String inicioPrestamo() {
        return "prestamo.html";
    }

    @GetMapping("prestamos/listar")//cualquiera de esas rutas
    public String listarPrestamos(ModelMap modelo) throws ErrorServicio {

        List<Prestamo> todos = pS.listarPrestamos();
        modelo.addAttribute("prestamos", todos);
        return "prestamo.html";

    }

    @GetMapping("/prestamos/nuevo")
    public String MostrarFormOtroPrestamo(ModelMap modelo) {
        try {
            Prestamo prestamo = new Prestamo();
            modelo.addAttribute("prestamos", prestamo);
            modelo.addAttribute("clientes", cS.listarClientes());
            modelo.addAttribute("libros", lS.listarLibros());
            return "form_prestamo.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }

    }

    @PostMapping("/prestamos/registro")
    public String guardarPrestamo(ModelMap modelo, @RequestParam String idCliente, @RequestParam String idLibro,
            @RequestParam String fechaPrestamo, @RequestParam String fechaDevolucion) throws ErrorServicio {
        try {
            Date fecha1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaPrestamo);
            Date fecha2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDevolucion);
            pS.archivarPrestamo(idCliente, idLibro, fecha1, fecha2);
            modelo.put("titulo", "LibreryService");
            modelo.put("descrip", "El préstamo fue cargado con éxito.");
            return "prestamoexito.html";

        } catch (Exception e) {

            modelo.put("error", e.getMessage());
            List<Cliente>clientes=cS.listarClientes();
            modelo.put("clientes", clientes);
            List<Libro>libros=lS.listarLibros();
            modelo.put("libros", libros);
            modelo.put("fechaPrestamo", fechaPrestamo);
            modelo.put("fechaDevolucion", fechaDevolucion);
            
            System.out.println(e.getMessage() + "Falló algo");
            return "form_prestamo.html";

        }
    }
    @PostMapping("/prestamos/finalizacionDePrestamo")
	public String finalizarPrestamo(ModelMap modelo, @RequestParam String idPrestamo,
			@RequestParam String fechaDevolucion) throws ParseException {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDevolucion);
			pS.devolucionLibroPrestamo(idPrestamo, date);
			modelo.put("titulo", "PRESTAMO");
			modelo.put("descripcion", "Cerrado de Forma Correcta!");
			
			if (pS.obtenerPrestamoPorId(idPrestamo).getMulta() > 0) {
				modelo.put("multa","RECORDA COBRAR LA MULTA DE " + pS.obtenerPrestamoPorId(idPrestamo).getMulta());
			}

			return "prestamoaviso.html";
		} catch (Exception ex) {
			modelo.put("error", ex.getMessage());
			return "prestamo.html";
		}
	}

//    @GetMapping("/libros/editar/{id}")
//    public String mostrarFormEditarPrestamo(@PathVariable String id, ModelMap modelo) throws ErrorServicio {
//
//        modelo.addAttribute("prestamo", pS.obtenerPrestamoPorId(id));
//        return "editar_prestamo.html";
//
//    }

//    @PostMapping("/libros/confirmar-edicion{id}")
//    public String actualizarLibros(@PathVariable String id, ModelMap modelo, @RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo, @RequestParam(required = false) Integer anio, @RequestParam(required = false) Integer ejemplares,
//            @RequestParam(required = false) Integer ejemplaresPrestados, @RequestParam(required = false) Integer ejemplaresRestantes) throws ErrorServicio {
//
//        try {
//            lService.actualizarLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);
////            modelo.put("exito","Registro exitoso");
//            return "redirect:/libros/listar";
//        } catch (Exception e) {
//            modelo.put("error", "Faltó algún dato me parece...");
//            return "form-libro";
//        }
//    }

//    @GetMapping("libros/eliminar/{id}")
//    public String eliminar(@PathVariable String id) {
//
//        try {
//            lService.eliminar(id);
//            return "redirect:/libros/listar";
//        } catch (Exception e) {
//            return "redirect:/";
//        }

//    }

    @GetMapping("prestamos/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            pS.baja(id);
            return "redirect:/prestamos/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

    @GetMapping("prestamos/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            pS.alta(id);
            return "redirect:/prestamos/listar";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

}
