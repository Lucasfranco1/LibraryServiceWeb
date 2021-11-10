package com.libreria.libreriaweb.servicios;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.entidades.Editorial;
import com.libreria.libreriaweb.entidades.Libro;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.repositorios.AutorRepositorio;
import com.libreria.libreriaweb.repositorios.EditorialRepositorio;
import com.libreria.libreriaweb.repositorios.LibroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private AutorServicio aS;

    @Autowired
    private EditorialServicio eS;

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Transactional
    public List<Libro> listarLibros() throws ErrorServicio {

        return libroRepositorio.findAll(); //findAll()Para buscar todos 

    }
  public List<Libro> ListarTodo(String nombre){
      return libroRepositorio.buscarLibroPorNombre(nombre);
  }
//    }

    @Transactional
    public void crearLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
            Integer ejemplaresRestantes) throws ErrorServicio {

        
            validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);

            Libro libro = new Libro();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(true);

            Autor autor=aS.obtenerAutorPorId(idAutor);
      
            if(autor==null){
                aS.guardarAutor(libro.getAutor().getNombre());
            }else{
                libro.setAutor(autor); 
            }
              

              Editorial editorial=eS.obtenerAutorPorId(idEditorial);
              if(editorial==null){
                  eS.guardarEditorial(libro.getEditorial().getNombre());
              }else{
                libro.setEditorial(editorial);  
              }              
            libroRepositorio.save(libro);
        

    }

    @Transactional
    public Libro obtenerLibroPorId(String id) throws ErrorServicio {

        //si el id no es nulo entonces se busca y se muestra
        Optional<Libro> resp = libroRepositorio.findById(id);
        if (resp.isPresent()) {
            return libroRepositorio.findById(id).get();
        } else {
            throw new ErrorServicio("No se encontró el Id.");

        }

    }

    @Transactional
    public Libro actualizarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorServicio {
        
        Optional<Libro> resp=libroRepositorio.findById(id);
        if(resp.isPresent()){
        Libro libro=resp.get();
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAnio(anio);
           
           return libroRepositorio.save(libro); 
        }else{
            throw new ErrorServicio("No existe un libro con el id solicitado.");
        }
        
        
    }

    @Transactional
    public void baja(String id) {
        Optional<Libro> entidad = libroRepositorio.findById(id);
        if (entidad.isPresent()) {
            Libro libro = entidad.get();
            libro.setAlta(false);
            libroRepositorio.save(libro);
        }
    }

    @Transactional
    public void alta(String id) {
        Optional<Libro> entidad = libroRepositorio.findById(id);
        if (entidad.isPresent()) {
            Libro libro = entidad.get();
            libro.setAlta(true);
            libroRepositorio.save(libro);
        }
    }

    @Transactional
    public void eliminar(String id) throws ErrorServicio {
        Optional<Libro> entidad = libroRepositorio.findById(id);
        if (entidad.isPresent()) {
            libroRepositorio.deleteById(id);
        } else {
            throw new ErrorServicio("Error al eliminar libro");
        }
    }

//    }
//    @Transactional
//    public void modificarLibro(String idLibro, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
//                                Integer ejemplaresRestantes, Long isbn) throws ErrorServicio{
//        
//        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);
//        
//        Optional<Libro>respuesta=libroRepositorio.findById(idLibro);
//        if(respuesta.isPresent()){
//            Libro libro=respuesta.get();
//            libro.setTitulo(titulo);
//            libro.setIsbn(isbn);
//            libro.setEjemplares(ejemplares);
//            libro.setEjemplaresPrestados(ejemplaresPrestados);
//            libro.setEjemplaresRestantes(ejemplaresRestantes);
//            libro.setAnio(anio);
//            libroRepositorio.save(libro);
//            
//        }else{
//            throw new ErrorServicio("No existe un libro con el id solicitado.");
//        }
//                
//    }
//    @Transactional
//    public void eliminar(String id) throws ErrorServicio{
//        Optional<Libro>resp=libroRepositorio.findById(id);
//        if(resp.isPresent()){
//            libroRepositorio.deleteById(id);
//        }else{
//            throw new ErrorServicio("No se encontró el id.");
//        }
//    }
    public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares,
            Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorServicio {

        if (isbn == null || isbn < 0) {
            throw new ErrorServicio("El isbn del libro no puede ser nulo, ni menor a 0.");
        }
        Libro libro=libroRepositorio.buscarporIsbn(isbn);
        if(libro!=null){
            throw new ErrorServicio("Ya existe ese isbn");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El título del libro no puede ser nulo, ni estar vacío.");
        }
        if (anio == null || anio < 0) {
            throw new ErrorServicio("El año del libro no puede ser nulo, ni menor a 0.");
        }
        if (ejemplares == null || ejemplares < 0) {
            throw new ErrorServicio("Los ejemplares no pueden ser nulos, ni menor a 0.");
        }
        if (ejemplaresPrestados == null || ejemplaresPrestados > ejemplares) {
            throw new ErrorServicio("Los ejemplares prestados no pueden ser nulos, ni mayores a la cantidad total.");
        }
        if (ejemplaresRestantes == null) {
            throw new ErrorServicio("Los ejemplares restantes no pueden ser nulos.");
        }
    }

//    public void buscarLibroPorTitulo(String titulo) throws ErrorServicio{
//        Optional<Libro>resp=libroRepositorio.findById(titulo);
//        if(resp.isPresent()){
//            libroRepositorio.buscarLibroPorTitulo(titulo);
//        }else{
//            throw new ErrorServicio("El título de ese libro no existe en la base de dato.");
//        }
//        
//    }
//    public void buscarLibroPorAutor(String autor) throws ErrorServicio{
//        Optional<Libro>resp=libroRepositorio.findById(autor);
//        if(resp.isPresent()){
//            libroRepositorio.buscarLibroPorTitulo(autor);
//        }else{
//            throw new ErrorServicio("El autor no existe en la base de dato.");
//        }
//        
//    }
//     public Libro buscarLibroNombre(String nombre){
//
//        return libroRepositorio.buscarPorNombre(nombre);
//    }
}
