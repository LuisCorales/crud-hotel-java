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
public class ColaMantenimiento {
    private NodoMantenimiento inicio, fin;

    public ColaMantenimiento() {
    } 
    
    public boolean esVacia(){
        return inicio==null && fin==null;
    }
    
    public void encolar(NodoMantenimiento dato){
        NodoMantenimiento nuevo=new NodoMantenimiento(dato.getDato(), null, null);
        if(esVacia()){
            inicio=fin=nuevo;
        }else{
            fin.setDerecha(nuevo);
            fin=nuevo;
        }
    }
    
    public NodoMantenimiento desencolar(){
        NodoMantenimiento aux=inicio;
        inicio=inicio.getDerecha();
        if(inicio==null){
            fin=null;
        }else{
            aux.setDerecha(null);
        }
        return aux;      
    }
    
}
