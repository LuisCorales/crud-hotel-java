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
public class Arista {
    private Nodo orig, dest;//Nodo o vertice de origen y de destino
    private double distancia;    

    public Arista(Nodo dest, double distancia) {
        this.dest = dest;
        this.distancia = distancia;
    }
    
    public Arista() {
    }   

    public void setOrig(Nodo orig) {
        this.orig = orig;
    }

    public void setDest(Nodo dest) {
        this.dest = dest;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public Nodo getOrig() {
        return orig;
    }

    public Nodo getDest() {
        return dest;
    }

    public double getDistancia() {
        return distancia;
    }
    
    public boolean equals(Object a){
        Arista otra = (Arista)a;
        return (orig==otra.orig && dest==otra.dest)|| (orig==otra.dest && dest==otra.orig);
    }
    
    @Override
    public String toString() {
        return "Desde origen: " + orig + " hasta destino: " + dest + ", distancia=" + distancia;
    }
}
