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
public class NodoFecha {
    private Fecha dato;
    private NodoFecha anterior;
    private NodoFecha siguiente;

    public NodoFecha(Fecha dato, NodoFecha anterior, NodoFecha siguiente) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public Fecha getDato() {
        return dato;
    }

    public void setDato(Fecha dato) {
        this.dato = dato;
    }

    public NodoFecha getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoFecha anterior) {
        this.anterior = anterior;
    }

    public NodoFecha getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoFecha siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
