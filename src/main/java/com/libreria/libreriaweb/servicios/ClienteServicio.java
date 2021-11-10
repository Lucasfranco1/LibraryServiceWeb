/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.servicios;

import com.libreria.libreriaweb.entidades.Cliente;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.libreria.libreriaweb.repositorios.ClienteRepository;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepository cR;
    
    
    public Cliente crearCliente(Long documento, String nombre, String apellido, String telefono) throws ErrorServicio{
        
        validar(documento, nombre, apellido, telefono);
        Cliente cliente=new Cliente();
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setAlta(true);
        
        return cR.save(cliente);
        
    }
    public Cliente modificarCliente(Cliente cliente) throws ErrorServicio{
       if (cliente != null) {

            return cR.save(cliente);

        } else {
            throw new ErrorServicio("El cliente no puede ser nulo.");

        }
    }
    
    @Transactional
    public void baja(String id) {
        Optional<Cliente> entidad = cR.findById(id);
        if (entidad.isPresent()) {
            Cliente cliente = entidad.get();
            cliente.setAlta(false);
            cR.save(cliente);
        }
    }

    @Transactional
    public void alta(String id) {
       Optional<Cliente> entidad = cR.findById(id);
        if (entidad.isPresent()) {
            Cliente cliente = entidad.get();
            cliente.setAlta(true);
            cR.save(cliente);
        }
    }

    private void validar(Long documento, String nombre, String apellido, String telefono) throws ErrorServicio {
        if (documento == null || documento < 0) {
            throw new ErrorServicio("El documento del cliente no puede ser nulo, ni menor a 0.");
        }
        Cliente dni= cR.buscarporDocumento(documento);
        if(dni!=null){
            throw new ErrorServicio("Ese dni ya existe");
        }
        if (nombre == null ||  nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del cliente no puede ser nulo, ni estar vacío.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del cliente no puede ser nulo, ni menor a 0.");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El teléfono no pueden ser nulo, ni vacío.");
        }
    }
    @Transactional
    public List<Cliente> listarClientes() throws ErrorServicio {

        return cR.findAll(); //findAll()Para buscar todos 
    }
    public Cliente buscarClienteId(String id){

        return cR.buscarPorId(id);
    }
}
