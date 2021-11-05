/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.servicios;

import com.libreria.libreriaweb.entidades.Libro;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import java.util.List;

/**
 *
 * @author lucas
 */
public interface LibroServicioImp {

    

    public List<Libro> listarAutores();
    
    public Libro guardarAutor(Libro libro)throws ErrorServicio;
    
//    public Libro obtenerLibroPorId(String id)throws ErrorServicio;
//    
//    public Libro actualizarLibro(Libro libro)throws ErrorServicio;
//    
//    public void eliminarLibro(String id)throws ErrorServicio;
//    
}
