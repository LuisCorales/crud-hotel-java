/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */
public class Mantenimiento {
    private String nombre;
    private Fecha inicio, fin;

    public Mantenimiento(String nombre, Fecha inicio, Fecha fin) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInicio(Fecha inicio) {
        this.inicio = inicio;
    }

    public void setFin(Fecha fin) {
        this.fin = fin;
    }

    public String getNombre() {
        return nombre;
    }

    public Fecha getInicio() {
        return inicio;
    }

    public Fecha getFin() {
        return fin;
    }
    
    public String imprimirReporte(){
        return "Nombre: "+this.nombre+", Inicio: "+this.inicio.toString()+", Fin: "+this.fin.toString()+"; ";
    }
    
    @Override
    public String toString(){
        return this.nombre+","+this.inicio.toString()+","+this.fin.toString()+";";
    }
}
