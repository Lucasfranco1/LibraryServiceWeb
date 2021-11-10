
package com.libreria.libreriaweb.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="editorial")
public class Editorial {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
//     @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(unique = true)
    private String nombre;
    private Boolean alta;
//    @OneToMany(mappedBy = "editorial")
//    private List<Libro>libros;

   

    
    
//    public void agregarLibro(Libro ellibro){
//        if(libros==null) libros=new ArrayList<>();
//        libros.add(ellibro);
//        ellibro.setEditorial(this);
//    }
    public Editorial(String nombre) {
        this.nombre = nombre;
    }

    public Editorial() {
        alta=true;
    }
    
   
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

//    @Override
//    public String toString() {
//        return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + ", libros=" + libros + '}';
//    }
    
    
    
}
