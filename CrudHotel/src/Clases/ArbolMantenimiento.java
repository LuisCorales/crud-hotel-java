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
public class ArbolMantenimiento {
    private NodoMantenimiento raiz;

    public void setRaiz(NodoMantenimiento raiz) {
        this.raiz = raiz;
    }

    public NodoMantenimiento getRaiz() {
        return raiz;
    }
    
    public ArbolMantenimiento(){
        raiz=null;
    }
    
    public ArbolMantenimiento(NodoMantenimiento raiz){
        this.raiz=raiz;
    }
    
    public void insertar(NodoMantenimiento raiz, NodoMantenimiento iz, NodoMantenimiento der){
        raiz.setIzquierda(iz);
        raiz.setDerecha(der);
    }
    
    public boolean esVacio(){
        return raiz==null;
    }
    
    public ArbolMantenimiento(Mantenimiento dato){
        raiz = new NodoMantenimiento(dato, null, null);
    }
    
    public ArbolMantenimiento(Mantenimiento dato, NodoMantenimiento iz, NodoMantenimiento der){
        raiz = new NodoMantenimiento(dato, iz, der);
    }
    
    public int nivel(String nombre, int nivel){      
        return nivel(raiz, nombre, nivel);
    }
    
    public int nivel(NodoMantenimiento actual, String nombre, int nivel){
        ColaMantenimiento c1=new ColaMantenimiento();
        ColaMantenimiento caux=new ColaMantenimiento();
        c1.encolar(raiz);
        while(!c1.esVacia()){
            NodoMantenimiento aux = c1.desencolar();
            if(aux.getIzquierda()!=null){
                c1.encolar(aux.getIzquierda());
                if(aux.getIzquierda().getDato().getNombre().equalsIgnoreCase(nombre)){
                    break;
                }
            }
            if(aux.getDerecha()!=null){
                c1.encolar(aux.getDerecha());
                if(aux.getIzquierda().getDato().getNombre().equalsIgnoreCase(nombre)){
                    break;
                }
            }
            nivel++;
            caux.encolar(aux);
        }
        return nivel;
    }
    
}
