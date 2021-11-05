
package com.libreria.libreriaweb.repositorios;


import com.libreria.libreriaweb.entidades.Libro;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    
//    @Query("SELECT l FROM Libro l WHERE l.titulo =:titulo")
//    public Libro buscarLibroPorTitulo(@Param("titulo") String titulo);
//    
   @Query("SELECT l FROM Libro l WHERE l.autor.nombre=: nombre")
    public List<Libro> buscarLibroPorNombre(@Param("nombre") String nombre);
////    
//    
//    @Query("SELECT l FROM Libro l")
//    public ArrayList<Libro> listarLibros();
//    
    
    
}
