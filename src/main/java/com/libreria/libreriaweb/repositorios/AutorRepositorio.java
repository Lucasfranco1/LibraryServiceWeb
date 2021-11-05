
package com.libreria.libreriaweb.repositorios;

import com.libreria.libreriaweb.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    @Query("SELECT a FROM Autor a WHERE a.nombre= :nombre")
    public List<Autor> buscarPorNombre(@Param("nombre") String nombre);
    
//    @Query("SELECT a FROM Autor a WHERE a.id =:id")
//    public Autor buscarPorId(@Param("id") String id);
}
