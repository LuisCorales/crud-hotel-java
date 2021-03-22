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
public class NodoMantenimiento {
    private Mantenimiento dato;
    private NodoMantenimiento izquierda, derecha;

    public NodoMantenimiento(Mantenimiento dato, NodoMantenimiento izquierda, NodoMantenimiento derecha) {
        this.dato = dato;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public void setDato(Mantenimiento dato) {
        this.dato = dato;
    }

    public void setIzquierda(NodoMantenimiento izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(NodoMantenimiento derecha) {
        this.derecha = derecha;
    }

    public Mantenimiento getDato() {
        return dato;
    }

    public NodoMantenimiento getIzquierda() {
        return izquierda;
    }

    public NodoMantenimiento getDerecha() {
        return derecha;
    }
    
    
}
