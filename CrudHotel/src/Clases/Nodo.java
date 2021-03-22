/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class Nodo {
    private String nombre;
    private ArrayList<Arista> aristas = new ArrayList<Arista>();
    private double distancia = Double.MAX_VALUE;  //distancia al origen
    private ArrayList<Nodo> camino = new ArrayList<Nodo>();
   
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.camino = new ArrayList<Nodo>();
    }
    
    public void addArista(Nodo destino, double distancia){
        Arista nueva = new Arista(destino, distancia);
        this.aristas.add(nueva);
    }

    public Nodo() {
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAristas(ArrayList<Arista> aristas) {
        this.aristas = aristas;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setCamino(ArrayList<Nodo> camino) {
        this.camino = camino;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Arista> getAristas() {
        return aristas;
    }  

    public double getDistancia() {
        return distancia;
    }

    public ArrayList<Nodo> getCamino() {
        return camino;
    }
    
}
