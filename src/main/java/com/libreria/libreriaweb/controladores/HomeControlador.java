/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaweb.controladores;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.exceptiones.ErrorServicio;
import com.libreria.libreriaweb.servicios.AutorServicio;
import com.libreria.libreriaweb.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lucas
 */
@Controller
@RequestMapping("/")
public class HomeControlador {
    @Autowired
    private LibroServicio libroservice;
    @Autowired
    private AutorServicio autorService;
    
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
   
     
    
    
    
}
