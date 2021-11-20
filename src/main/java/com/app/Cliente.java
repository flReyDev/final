package com.app;

import java.time.LocalDate;

/**
 *
 * @author leonardo
 */
public class Cliente {
    
    private String nombre;
    private int id;
    private String servicio;
    private LocalDate fecha;

    public Cliente() {
    }
    
    public Cliente(String nombre, int id, String servicio, LocalDate fecha) {
        this.nombre = nombre;
        this.id = id;
        this.servicio = servicio;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
    
}
