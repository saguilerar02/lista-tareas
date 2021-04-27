package com.listatareas.listatareas.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tarea")
public class Tarea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String descripcion;

    @Column(columnDefinition="tinyint(1) default 1")
    private Boolean activo;

    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, Boolean activo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.activo = activo;
    }
    public Tarea(Integer id, String titulo, String descripcion, Boolean activo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Boolean getActive() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Tarea [activo=" + activo + ", descripcion=" + descripcion + ", id=" + id + ", titulo=" + titulo + "]";
    }
   


    
    
}
