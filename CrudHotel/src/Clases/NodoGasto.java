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
public class NodoGasto {
    private Gasto dato;
    private NodoGasto anterior, siguiente;

    public NodoGasto(Gasto dato, NodoGasto anterior, NodoGasto siguiente) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public Gasto getDato() {
        return dato;
    }

    public void setDato(Gasto dato) {
        this.dato = dato;
    }

    public NodoGasto getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoGasto anterior) {
        this.anterior = anterior;
    }

    public NodoGasto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGasto siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
