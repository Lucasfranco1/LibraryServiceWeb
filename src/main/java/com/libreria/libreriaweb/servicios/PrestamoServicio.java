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

@Service
public class PrestamoServicio {
    @Autowired
    private PrestamoRepositorio pR;
    @Autowired
    private LibroServicio lS;
    @Autowired
    private ClienteServicio cS;
    
//    @Transactional 
//	public void archivarPrestamo(String idCliente,String idLibro, Date fechaPrestamo, Date fechaDevolucion)throws ErrorServicio {
//		
//		Cliente cliente = cS.buscarClienteId(idCliente);	
//		Libro libro = lS.obtenerLibroPorId(idLibro);
//			
//		Prestamo prestamo = new Prestamo();
//					
//		if(libro.getEjemplaresPrestados()==libro.getEjemplares()||libro.getEjemplaresPrestados()>libro.getEjemplares()){
//			throw new ErrorServicio("No hay ejemplares disponibles, por favor elija otro libro");
//		}else if(fechaPrestamo.compareTo(fechaDevolucion)>=0){					
//			throw new ErrorServicio("La fecha programada de devolucion no puede ser anterior o igual a la fecha de entrega del ejemplar");
//		}else{	
//			prestamo.setCliente(cliente);
//			prestamo.setFechaPrestamo(fechaPrestamo);
//			prestamo.setFechaDevolucion(fechaDevolucion);			
//			prestamo.setLibro(libro);
//                        prestamo.setMulta(0);
//			prestamo.setEstadoPrestamo(EstadoPrestamo.COMENZADO);
//			libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
//                        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
//			pR.save(prestamo);	 
//			}
//	}
//
//
//
//	@Transactional 
//	public void devolucionLibroPrestamo(String idPrestamo,Date fechaDevolucion) throws ErrorServicio {
//		
//		Prestamo prestamo = pR.buscarPrestamoporId(idPrestamo);               
//                String idLibro= prestamo.getLibro().getId();
//		Libro libro = lS.obtenerLibroPorId(idLibro);
//	
//		if(fechaDevolucion.compareTo(prestamo.getFechaDevolucion())> 0) {					
//			prestamo.setMulta(calcularMultaFinal(fechaDevolucion,prestamo.getFechaDevolucion()));	
//		}
//		
//		
//		libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()-1);	
//                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
//		prestamo.setEstadoPrestamo(EstadoPrestamo.TERMINADO);
//		prestamo.setFechaDevolucion(fechaDevolucion);
//                
//		pR.save(prestamo);
//                
//							
//	}
//	
//	
//            public Integer calcularMultaFinal(Date fechaDevolucion, Date devolucion) {		
//		int precioXDia= 30;		
//		
//		int cantidadDias =(int) (fechaDevolucion.getTime()-devolucion.getTime())/86400000;		
//		int multa = cantidadDias * precioXDia;
//	
//		return multa;
//}
//	



    

}
