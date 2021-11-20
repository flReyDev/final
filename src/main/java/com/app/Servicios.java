package com.app;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leonardo
 */
public class Servicios { 
    private final Map<String, Integer> services;
    
    private static Servicios servicio;
    
    
    private Servicios(){
        this.services = new HashMap<>();
        this.services.put("Asesoria", 20);
        this.services.put("Planeación", 20);
        this.services.put("Compra",10);
        this.services.put("Venta",10);
        this.services.put("PQR",10);
        this.services.put("Hipotecas",20);
        this.services.put("Recuperación",10);
    }
    
    
    public static Servicios GetServicios(){
        if(servicio == null){
            servicio = new Servicios();
        }
        return servicio;
    }
    
    
    public boolean validar(String service){
        boolean validado = false;
        if(this.services.containsKey(service)){
           if(this.services.get(service) != 0){
               validado = true;
               this.services.put(service, this.services.get(service)-1);
           }
        }else{
            System.err.println("Servicio no encontrado");
        }
        return validado;
    }
    
    /**
     * Regresar el estado a 1 del servicio que se utilizo
     * @param cadena 
     */
    public void habilitar(String cadena){
        if(!cadena.equals("No hay turnos por atender")){
            String[] data = cadena.split("-");
            data[0] = null;
            String[] data2 = data[1].split(" ");
            this.services.put(data2[0], +1);
            data = null;
            data2 = null;
        }
    }
    
}
