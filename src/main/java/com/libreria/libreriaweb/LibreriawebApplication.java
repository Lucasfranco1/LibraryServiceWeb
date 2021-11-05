package com.libreria.libreriaweb;

import com.libreria.libreriaweb.entidades.Autor;
import com.libreria.libreriaweb.entidades.Editorial;
import com.libreria.libreriaweb.entidades.Libro;
import com.libreria.libreriaweb.repositorios.AutorRepositorio;
import com.libreria.libreriaweb.repositorios.EditorialRepositorio;
import com.libreria.libreriaweb.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class LibreriawebApplication{

	public static void main(String[] args){
		SpringApplication.run(LibreriawebApplication.class, args);
	}
        
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

//    @Override
//    public void run(String... args) throws Exception {
//        Editorial editorial=new Editorial("Perlas");
//        editorialRepositorio.save(editorial);
//        Autor autor=new Autor("Darío Guiñazú");
//        autorRepositorio.save(autor);
//        Libro libro=new Libro(13456L, 2020, "La Casa Embrujada", 500, 200, 300, editorial, autor);
//        libroRepositorio.save(libro);
//    }

}
