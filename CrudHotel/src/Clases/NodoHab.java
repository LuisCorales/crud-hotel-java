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
public class NodoHab {
    private Habitacion dato;
    private NodoHab anterior;
    private NodoHab siguiente;

    public NodoHab(Habitacion dato, NodoHab anterior, NodoHab siguiente) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public void setDato(Habitacion dato) {
        this.dato = dato;
    }

    public void setAnterior(NodoHab anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(NodoHab siguiente) {
        this.siguiente = siguiente;
    }

    public Habitacion getDato() {
        return dato;
    }

    public NodoHab getAnterior() {
        return anterior;
    }

    public NodoHab getSiguiente() {
        return siguiente;
    }
    
    
}
