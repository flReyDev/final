package com.app;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class DBClientes{
    
    private final List<String> turnos;
    private static DBClientes bClientes;
    
    
    /**
     * Uso del patron Singleton
     */
    private DBClientes(){
        this.turnos = new ArrayList<>();
    }
    
    /**
     * Obtener al instancia de la clase
     * @return 
     */
    public static DBClientes getBClientes(){
        if(bClientes == null){
            bClientes = new DBClientes();
        }
        return bClientes;
    }
    
    /**
     * Agregar
     * @param info 
     */
    public void push(String info){
        this.turnos.add(info);
    }
    
    /**
     * Quitar el primer elemento
     */
    public void pop(){
        if(this.turnos.isEmpty()){
            System.out.println("No hay turnos actualmente");
        }else{
            this.turnos.remove(0);
        }
    }
    
    /**
     * Mostrar primer turno
     * @return 
     */
    public String fisrt(){
        String first = "";
        if(!this.turnos.isEmpty()){
            first+= this.turnos.get(0);
        }else{
            first = "No hay turnos por atender";
        }
        return first;
    }
    
    /*
    Mostrar el ultimo usuario que se registro
    */
    public String last(){
        String last = "";
        if(!this.turnos.isEmpty()){
            int n = this.turnos.size()-1;
            last+= this.turnos.get(n);
        }
        return last;
    }
    
    /**
     * Mostrar
     * @return 
     */
    public String show(){
        
        String lista = "";
        
        for(int i=0; i<this.turnos.size(); i++){
            String[] turner = this.turnos.get(i).split("-");  
            lista += "Turno: "+turner[0]+"\n Usuario: "+turner[1]+"\n";
        }
        return lista;
    }
    
    
    
    /**
     * Tamaño
     * @return 
     */
    public int cola(){
        return this.turnos.size();
    }
    
    /**
     * Limpiar
     */
    public void clean(){
        this.turnos.clear();
    }
    
    
}
