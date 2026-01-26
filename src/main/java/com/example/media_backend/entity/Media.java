package com.example.media_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String tipo; // pelicula / serie
    private Integer anio;
    private Double calificacion;
    private String estado; // visto / no visto

    public Media() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getAnio() {
        return anio;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
