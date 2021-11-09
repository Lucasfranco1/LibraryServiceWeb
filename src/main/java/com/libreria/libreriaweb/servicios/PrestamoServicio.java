/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.servicios;

import com.libreria.libreriaweb.entidades.Cliente;
import com.libreria.libreriaweb.entidades.Libro;
import com.libreria.libreriaweb.entidades.Prestamo;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.repositorios.PrestamoRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServicio {
    @Autowired
    private PrestamoRepositorio pR;
    @Autowired
    private LibroServicio lS;
    
//    public Prestamo crearPrestamo(Date fechaPrestamo, Date fechaDevolucion, String  nombreLibro, String  nombreCliente) throws ErrorServicio{
//        validar(fechaPrestamo, fechaDevolucion);
//        
//        
//        Prestamo p=new Prestamo();
//            p.setFechaPrestamo(fechaPrestamo);
//            p.setFechaDevolucion(fechaDevolucion);
//           
//
//            Libro libro=lS.buscarLibroNombre(nombreLibro);
//      
//            if(libro!=null){
//                libro.setAutor(autor);
//            }else{
//                 
//            }
//              
//
//              Editorial editorial=eS.obtenerEditorialNombre(nombreEditorial);
//              if(editorial==null){
//                  eS.guardarEditorial(libro.getEditorial().getNombre());
//              }else{
//                libro.setEditorial(editorial);  
//              }              
//            libroRepositorio.save(libro);
//        } catch (Exception e) {
//            System.out.println(e.getMessage() + "Error al cargar libro");
//        }

        
        
        
//    }
//
//    private void validar(Date fechaPrestamo, Date fechaDevolucion) throws ErrorServicio {
//        if (fechaPrestamo == null) {
//            throw new ErrorServicio("La fecha no puede ser nula");
//        }
//        if (fechaDevolucion == null) {
//            throw new ErrorServicio("La fecha no puede ser nula, ni estar vacío.");
//        }
//        
//    }
}
