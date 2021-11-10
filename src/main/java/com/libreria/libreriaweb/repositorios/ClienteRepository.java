/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.repositorios;

import com.libreria.libreriaweb.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author lucas
 */
@Repository
public interface ClienteRepository extends  JpaRepository<Cliente, String>{

    @Query("SELECT c FROM Cliente c WHERE c.id= :id")
    public Cliente buscarPorId(@Param("id") String id);
//
//    @Query("SELECT c FROM Cliente c ORDER BY c.nombre ASC")
//    public List<Cliente> buscarTodos();
//
   @Query("SELECT c FROM Cliente c WHERE c.documento= :documento")
    public Cliente buscarporDocumento(@Param("documento") Long documento);
}
