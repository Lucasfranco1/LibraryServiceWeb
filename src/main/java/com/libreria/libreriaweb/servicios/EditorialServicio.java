
package com.libreria.libreriaweb.servicios;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.entidades.Editorial;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EditorialServicio{
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
   
    @Transactional
    public List<Editorial> listarEditoriales() throws ErrorServicio {

        return editorialRepositorio.findAll(); //findAll()Para buscar todos 
    }

    @Transactional
    public Editorial guardarEditorial(String nombre) throws ErrorServicio {

        try {
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorial.setAlta(true);

            return editorialRepositorio.save(editorial);

        } catch (Exception e) {
            throw new ErrorServicio("La editorial no puede ser nula.");
        }

    }
    public Editorial obtenerEditorialNombre(String nombre){
        return editorialRepositorio.buscarPorNombre(nombre);
    }

    @Transactional
    public Editorial obtenerAutorPorId(String id) throws ErrorServicio {

        //si el id no es nulo entonces se busca y se muestra
        Optional<Editorial> resp = editorialRepositorio.findById(id);
        if (resp.isPresent()) {
            return editorialRepositorio.findById(id).get();
        } else {
            throw new ErrorServicio("No se encontró el Id.");

        }

    }

    @Transactional
    public Editorial actualizarAutor(Editorial editorial) throws ErrorServicio {

        if (editorial != null) {

            return editorialRepositorio.save(editorial);

        } else {
            throw new ErrorServicio("La editorial no puede ser nulo.");

        }
    }

    @Transactional
    public void baja(String id) {
        Optional<Editorial> entidad = editorialRepositorio.findById(id);
        if (entidad.isPresent()) {
            Editorial editorial= entidad.get();
            editorial.setAlta(false);
            editorialRepositorio.save(editorial);
        }

    }
    @Transactional
    public void alta(String id) {
        Optional<Editorial> entidad = editorialRepositorio.findById(id);
        if (entidad.isPresent()) {
            Editorial editorial= entidad.get();
            editorial.setAlta(true);
            editorialRepositorio.save(editorial);
        }

    }
    @Transactional
    public void eliminar(String id) throws ErrorServicio{
         Optional<Editorial> entidad = editorialRepositorio.findById(id);
         if(entidad.isPresent()){
            editorialRepositorio.deleteById(id);
        }else{
             throw new ErrorServicio("Error al eliminar la editorial");
         }
    }

}

