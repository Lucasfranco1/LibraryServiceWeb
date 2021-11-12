/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.servicios;

import com.libreria.libreriaweb.entidades.Cliente;
import com.libreria.libreriaweb.entidades.Libro;
import com.libreria.libreriaweb.entidades.Prestamo;
import com.libreria.libreriaweb.enumeraciones.EstadoPrestamo;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.repositorios.PrestamoRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libreria.libreriaweb.repositorios.ClienteRepository;
import java.util.List;

@Service
public class PrestamoServicio {

    @Autowired
    private PrestamoRepositorio pR;
    @Autowired
    private LibroServicio lS;
    @Autowired
    private ClienteServicio cS;

    @Transactional
    public void archivarPrestamo(String idCliente, String idLibro, Date fechaPrestamo, Date fechaDevolucion) throws ErrorServicio {

        Cliente cliente = cS.buscarClienteId(idCliente);
        Libro libro = lS.obtenerLibroPorId(idLibro);

        Prestamo prestamo = new Prestamo();

        if (libro.getEjemplaresPrestados() == libro.getEjemplares() || libro.getEjemplaresPrestados() > libro.getEjemplares()) {
            throw new ErrorServicio("No hay ejemplares disponibles, por favor elija otro libro");
        } else if (fechaPrestamo.compareTo(fechaDevolucion) >= 0) {
            throw new ErrorServicio("La fecha programada de devolucion no puede ser anterior o igual a la fecha de entrega del ejemplar");
        } else {
            prestamo.setCliente(cliente);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);
            prestamo.setLibro(libro);
            prestamo.setMulta(0);
            prestamo.setEstadoPrestamo(EstadoPrestamo.COMENZADO);
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
            pR.save(prestamo);
        }
    }

//
//
    @Transactional
    public void devolucionLibroPrestamo(String idPrestamo, Date fechaDevolucion) throws ErrorServicio {

        Prestamo prestamo = pR.buscarPrestamoporId(idPrestamo);
        String idLibro = prestamo.getLibro().getId();
        Libro libro = lS.obtenerLibroPorId(idLibro);

        if (fechaDevolucion.compareTo(prestamo.getFechaDevolucion()) > 0) {
            prestamo.setMulta(calcularMultaFinal(fechaDevolucion, prestamo.getFechaDevolucion()));
        }

        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
        prestamo.setEstadoPrestamo(EstadoPrestamo.TERMINADO);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setAlta(false);

        pR.save(prestamo);

    }

//	
    public Integer calcularMultaFinal(Date fechaDevolucion, Date devolucion) {
        int precioXDia = 30;

        int cantidadDias = (int) (fechaDevolucion.getTime() - devolucion.getTime()) / 86400000;
        int multa = cantidadDias * precioXDia;

        return multa;
    }

    @Transactional
    public List<Prestamo> listarPrestamos() throws ErrorServicio {

        return pR.findAll(); //findAll()Para buscar todos 
    }
    @Transactional
    public Prestamo obtenerPrestamoPorId(String id) throws ErrorServicio {

        //si el id no es nulo entonces se busca y se muestra
        Optional<Prestamo> resp = pR.findById(id);
        if (resp.isPresent()) {
            return pR.findById(id).get();
        } else {
            throw new ErrorServicio("No se encontró el Id.");

        }

    }
    
    public void comprobarQueSeaActivo(String id) throws ErrorServicio{
        if (pR.getById(id).getAlta()== false) {
               throw new ErrorServicio("Este préstamo ya está inactivo");
            }
    }
    
    @Transactional
    public void baja(String id) {
        Optional<Prestamo> entidad = pR.findById(id);
        if (entidad.isPresent()) {
            Prestamo prestamo = entidad.get();
            prestamo.setAlta(false);
            pR.save(prestamo);
        }
    }
    @Transactional
    public void alta(String id) {
        Optional<Prestamo> entidad = pR.findById(id);
        if (entidad.isPresent()) {
            Prestamo prestamo = entidad.get();
            prestamo.setAlta(true);
            pR.save(prestamo);
        }
    }
    @Transactional
    public void eliminar(String id) throws ErrorServicio{
         Optional<Prestamo> entidad = pR.findById(id);
         if(entidad.isPresent()){
         pR.deleteById(id);
        }else{
             throw new ErrorServicio("Error al eliminar préstamo");
         }
    }
}
