/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.servicios;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lucas
 */
@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public List<Autor> listarAutores() throws ErrorServicio {

        return autorRepositorio.findAll(); //findAll()Para buscar todos 
    }

    @Transactional
    public Autor guardarAutor(String nombre) throws ErrorServicio {

        try {
            Autor autor = new Autor();
            autor.setNombre(nombre);
            autor.setAlta(true);

            return autorRepositorio.save(autor);

        } catch (Exception e) {
            throw new ErrorServicio("El autor no puede ser nulo.");
        }

    }

    @Transactional
    public Autor obtenerAutorPorId(String id) throws ErrorServicio {

        //si el id no es nulo entonces se busca y se muestra
        Optional<Autor> resp = autorRepositorio.findById(id);
        if (resp.isPresent()) {
            return autorRepositorio.findById(id).get();
        } else {
            throw new ErrorServicio("No se encontró el Id.");

        }

    }

    @Transactional
    public Autor actualizarAutor(Autor autor) throws ErrorServicio {

        if (autor != null) {

            return autorRepositorio.save(autor);

        } else {
            throw new ErrorServicio("El autor no puede ser nulo.");

        }
    }
    public Autor buscarAutorNombre(String nombre){

        return autorRepositorio.buscarPorNombre(nombre);
    }

    @Transactional
    public void baja(String id) {
        Optional<Autor> entidad = autorRepositorio.findById(id);
        if (entidad.isPresent()) {
            Autor autor= entidad.get();
            autor.setAlta(false);
            autorRepositorio.save(autor);
        }

    }
    @Transactional
    public void alta(String id) {
        Optional<Autor> entidad = autorRepositorio.findById(id);
        if (entidad.isPresent()) {
            Autor autor= entidad.get();
            autor.setAlta(true);
            autorRepositorio.save(autor);
        }

    }
    @Transactional
    public void eliminar(String id) throws ErrorServicio{
         Optional<Autor> entidad = autorRepositorio.findById(id);
         if(entidad.isPresent()){
         autorRepositorio.deleteById(id);
        }else{
             throw new ErrorServicio("Error al eliminar autor");
         }
    }

}
//
//    }
//    @Transactional
//    @Override
//    public void eliminarAutor(String id) throws ErrorServicio {
//        Optional<Autor> resp = autorRepositorio.findById(id);
//        if (resp.isPresent()) {
//            autorRepositorio.deleteById(id);
//        } else {
//            throw new ErrorServicio("No se encuentra ese id.");
//        }
//    }

