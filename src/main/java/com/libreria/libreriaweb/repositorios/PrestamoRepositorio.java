/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.repositorios;

import com.libreria.libreriaweb.entidades.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String>{
        
        @Query("SELECT a FROM Prestamo a WHERE a.id= :id")
	 public Prestamo buscarPrestamoporId(@Param("id")String id); 		
	 
//	 @Query("SELECT a FROM Prestamo a ORDER BY a.fecha ASC")
//	 public List<Prestamo> buscarTodos();	
}
