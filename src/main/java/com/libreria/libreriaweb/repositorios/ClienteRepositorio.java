/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.repositorios;

import com.libreria.libreriaweb.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>  {
    @Query("SELECT a FROM Cliente a WHERE a.nombre= :nombre")
    public Cliente buscarPorNombre(@Param("nombre") String nombre);
}
